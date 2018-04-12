package fr.wildcodeschool.punkapi;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * PunkBeer
 * Simple app for communicating with the Punk API
 * By julienTorres
 *
 */
public class App 
{
    public static void main(String[] args) {


        try(InputStream is = new URL("https://api.punkapi.com/v2/beers/1").openStream();
            JsonReader reader =  Json.createReader(new InputStreamReader(is, "UTF-8"))){

            //TODO: Let's start doing Yoga with Json
            JsonArray received = reader.readArray();
            System.out.println(received);

        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
