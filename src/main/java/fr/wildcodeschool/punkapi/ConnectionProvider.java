package fr.wildcodeschool.punkapi;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionProvider {

    private static ConnectionProvider connection;

    //Constructeur privé
    private ConnectionProvider(String complementUrl){
        String baseUrl = "https://api.punkapi.com/v2/";

        try (
            //connection = DriverManager.getConnection(url, user, passwd);
            InputStream is = new URL(baseUrl + complementUrl).openStream();


            JsonReader reader =  Json.createReader(new InputStreamReader(is, "UTF-8"))) {

                //TODO: Let's start doing Yoga with Json
                JsonArray received = reader.readArray();
                System.out.println(received);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
    public static ConnectionProvider connect(String complementUrl){
        if(connection == null){
            new ConnectionProvider(complementUrl);
        }
        return connection;
    }

}
