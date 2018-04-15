package fr.wildcodeschool.punkapi;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BeerUtils {

    // private
    private static final String baseUrl = "https://api.punkapi.com/v2/beers";
    private static JsonArray received;

    // package private
    static List<Beer> beers = new ArrayList<>();
    static List<Beer> customList = new ArrayList<>();


    /** récupère la liste de toutes les bières
     *
     */
    public static void getAllBeers() {

        int index = 1;
        String url;
        beers.clear();

        do {
            url = baseUrl + "?page=" + index++ + "&per_page=25";//TODO remettre 80
            fetchBeer(url);
        } while (received != null && received.size() == 80);
    }


    // récupère une bière par son id.
    public static void getBeerBy(int id) {
        String url = baseUrl + "/" + id;
        beers.clear();

        fetchBeer(url);
    }


    // récupérer les bières contenant moins (ou plus) d'une certaine quantité d'un ingrédient.
    public static void getBeerByIngredient(String ingredient, boolean moreOrLess, double quantity) {
        // recupère toutes les bières
        getAllBeers();
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
    }




    // récupérer les bières selon la valeur d'une de leurs caractéristiques
    public static void getBeerBy(String characteristic, boolean moreOrLess, int quantity) {

        String choiceComplement = moreOrLess ? "_gt" : "_lt";
        String url = baseUrl + "?" + characteristic + choiceComplement + "=" + quantity;
        beers.clear();

        fetchBeer(url);

        //TODO gérer le cas ou rien n'est retourné ex abv 80+
    }


    // récupérer la liste des bières en fonction d'un pattern de recherche dans le nom
    public static void getBeerBy(String name) {

        String url = baseUrl + "?beer_name=" + name;
        beers.clear();

        fetchBeer(url);

        //TODO gérer le cas ou rien n'est retourné ex abv 80+
    }


    //se connecte à l'api sur l'url fournie et retourne le json reçu
    private static void fetchBeer(String url) {
        try (InputStream is = new URL(url).openStream();
             JsonReader reader = Json.createReader(new InputStreamReader(is, "UTF-8"))
        ) {
            received = reader.readArray();
            for (int i = 0 ; i <= received.size() - 1 ; i++) {
                Beer beer = BeerFactory.buildBeer(received.getJsonObject(i));
                beers.add(beer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}