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

    private static final String baseUrl = "https://api.punkapi.com/v2/beers";


    // récupère la liste de toutes les bières
    public static void getAllBeers() {

        JsonArray received;
        int counter = 0;
        int index = 0;

        do {
            try (   InputStream is = new URL(baseUrl + "?page=" + ++index + "&per_page=80").openStream();
                    JsonReader reader =  Json.createReader(new InputStreamReader(is, "UTF-8"))
            ) {
                received = reader.readArray();
                BeerFactory.buildBeerList(received);

                counter = 0;
                counter = received.size();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (counter == 80);
    }


    // récupère une bière par son id.
    public static Beer getBeerBy(int id) {
        Beer beer = new Beer();
        String url = baseUrl + "/" + id;

        try (   InputStream is = new URL(url).openStream();
                JsonReader reader =  Json.createReader(new InputStreamReader(is, "UTF-8"))
            ) {
            //TODO: Let's start doing Yoga with Json
            JsonArray received = reader.readArray();
            System.out.println(received);

            beer = BeerFactory.buildBeer(received.getJsonObject(0));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return beer;
    }

    // récupérer les bières contenant moins (ou plus) d'une certaine quantité d'un ingrédient.
    public static List<Beer> getBeerBy(IngredientQuery ingredient, boolean moreOrLess, int quantity) {
        List<Beer> beers = new ArrayList<>();

        String choiceComplement = moreOrLess ? "_gt" : "_lt";
        String url = baseUrl + "?" + ingredient.getName() + choiceComplement + "=" + quantity;

        try (   InputStream is = new URL(url).openStream();
                JsonReader reader = Json.createReader(new InputStreamReader(is, "UTF-8"))
        ) {
            JsonArray received = reader.readArray();

            for (int i = 1 ; i < received.size() ; i++) {
                Beer beer = BeerFactory.buildBeer(received.getJsonObject(i));
                beers.add(beer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return beers;//TODO gérer le cas ou rien n'est retourné ex abv 80+
    }

    // récupérer la liste des bières en fonction d'un pattern de recherche dans le nom
    public static List<Beer> getBeerBy(String name) {
        List<Beer> beers = new ArrayList<>();

        String url = baseUrl + "?beer_name=" + name;

        try (   InputStream is = new URL(url).openStream();
                JsonReader reader = Json.createReader(new InputStreamReader(is, "UTF-8"))
        ) {
            JsonArray received = reader.readArray();

            for (int i = 1 ; i < received.size() ; i++) {
                Beer beer = BeerFactory.buildBeer(received.getJsonObject(i));
                beers.add(beer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return beers;//TODO gérer le cas ou rien n'est retourné ex abv 80+
    }

}

