package fr.wildcodeschool.punkapi;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;
import static fr.wildcodeschool.punkapi.PunkBeerDAO.*;


public class BeerUtils {

    // private
    private static final String baseUrl = "https://api.punkapi.com/v2/beers";

    /**
     * Récupère la liste de toutes les bières.
     */
    public static List<Beer> getAllBeers() {//TODO virer les static

        int index = 1;
        String url;
        List<Beer> beers = new ArrayList<>();

        do {
            url = baseUrl + "?page=" + index++ + "&per_page=80";//TODO remettre 80
            beers = fetchBeer(url);
        } while (beers != null && beers.size() == 80);

        return beers;
    }


    /**
     * Récupère une bière par son id.
     * @param id L'id de la bière recherchée.
     */
    public static List<Beer> getBeerBy(int id) {//return liste de 1 item !!
        String url = baseUrl + "/" + id;
        List<Beer> beers = new ArrayList<>();

        beers = fetchBeer(url);

        return beers;//Liste de 1 item !!
    }


    /**
     * Récupère les bières contenant moins (ou plus) d'une certaine quantité d'un ingrédient.
     * @param ingredient Le nom de l'ingrédient recherché.
     * @param moreOrLess True si on recherche les bières contenant une quantité supérieure à la valeur fournie, False sinon.
     * @param quantity Le critère de recherche quantité.
     */
    public static List<Beer> getBeerByIngredient(String ingredient, boolean moreOrLess, double quantity) {
        // recupère toutes les bières
        List<Beer> beers = getAllBeers();
        List<Beer> customList = new ArrayList<>();
        //iterate beers
        for(int i = 0; i < beers.size(); i++) {
            //check ingredients for one specific ingredient
            List<Ingredient> ingredientList = beers.get(i).getIngredients();
            for(int j = 0; j < ingredientList.size(); j++) {
                if (ingredientList.get(j).getName().equals(ingredient)
                    && (ingredientList.get(j).getAmountValue() > quantity) == moreOrLess) {
                        // store any beer containing the right amount of that ingredient in a new list
                        customList.add(beers.get(i));
                }
            }
        }

        return customList;
    }


    /**
     * Récupère les bières selon la valeur d'une de leurs caractéristiques.
     * @param characteristic Une des caractéristiques d'une bière.
     * @param moreOrLess True si on recherche les bières contenant une valeur supérieure à la valeur fournie, False sinon.
     * @param quantity Le critère de recherche quantité.
     */
    public static List<Beer> getBeerBy(String characteristic, boolean moreOrLess, int quantity) {

        String choiceComplement = moreOrLess ? "_gt" : "_lt";
        String url = baseUrl + "?" + characteristic + choiceComplement + "=" + quantity;
        List<Beer> beers = new ArrayList<>();

        beers = fetchBeer(url);

        //TODO gérer le cas ou rien n'est retourné ex abv 80+
        return beers;
    }


    /**
     * Récupère la liste des bières en fonction de son nom.
     * @param name Le nom recherché.
     */
    public static List<Beer> getBeerBy(String name) {

        String url = baseUrl + "?beer_name=" + name;
        List<Beer> beers = new ArrayList<>();

        beers = fetchBeer(url);

        //TODO gérer le cas ou rien n'est retourné ex abv 80+
        return beers;
    }

    //Récupère la liste des bières en fonction d'un pattern de recherche dans le nom


}