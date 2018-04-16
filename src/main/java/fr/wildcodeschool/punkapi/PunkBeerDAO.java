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


public class PunkBeerDAO {

    /**
     * Se connecte à l'api sur l'url fournie et une liste de bières.
     * @param url L'url à laquelle se connecter.
     * @return La liste de bières.
     */
    public static List<Beer> fetchBeer(String url) {

        List<Beer> beers = new ArrayList<>();
        JsonArray received;

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

        return beers;
    }

}