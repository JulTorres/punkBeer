package fr.wildcodeschool.punkapi;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class BeerFactory {


    public static Beer buildBeer(JsonArray received) {
        
        Beer beer = new Beer();
/*
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
*/

        JsonObject receivedObject = received.getJsonObject(0);


        beer.setId(receivedObject.getInt("id"));
        beer.setName(receivedObject.getString("name"));
        beer.setDescription(receivedObject.getString("description"));
        beer.setImageUrl(receivedObject.getString("image_url"));
        beer.setAbv(receivedObject.getJsonNumber("abv").doubleValue());
        beer.setIbu(receivedObject.getJsonNumber("ibu").doubleValue());
        beer.setTargetFg(receivedObject.getJsonNumber("target_fg").doubleValue());
        beer.setTargetOg(receivedObject.getJsonNumber("target_og").doubleValue());
        beer.setEbc(receivedObject.getJsonNumber("ebc").doubleValue());
        beer.setSrm(receivedObject.getJsonNumber("srm").doubleValue());
        beer.setPh(receivedObject.getJsonNumber("ph").doubleValue());
        beer.setAttenuationLevel(receivedObject.getJsonNumber("attenuation_level").doubleValue());

        JsonObject volumeObject = receivedObject.getJsonObject("volume");
        
        beer.setVolume(new Volume(
                volumeObject.getInt("value"),
                volumeObject.getString("unit")
        ));

        beer.setBoilVolume(new BoilVolume(
                receivedObject.getJsonObject("boil_volume").getInt("value"),
                volumeObject.getString("unit")
        ));

        JsonObject mashTempObject = receivedObject.getJsonObject("method").getJsonArray("mash_temp").getJsonObject(0).getJsonObject("temp");
        Temp mashTempTemp = new Temp(
                mashTempObject.getJsonNumber("value").doubleValue(),
                mashTempObject.getString("unit")
        );
        MashTemp mashTemp = new MashTemp(
                mashTempTemp,
                receivedObject.getJsonObject("method").getJsonArray("mash_temp").getJsonObject(0).getInt("duration")
        );

        List<MashTemp> mashTemps = new ArrayList<>();
        mashTemps.add(mashTemp);

        Temp fermentationTemp = new Temp (
                receivedObject.getJsonObject("method").getJsonObject("fermentation").getJsonObject("temp").getJsonNumber("value").doubleValue(),
                receivedObject.getJsonObject("method").getJsonObject("fermentation").getJsonObject("temp").getString("unit")
        );

        Fermentation fermentation = new Fermentation(fermentationTemp);

        Method method = new Method(mashTemps, fermentation, receivedObject.getJsonObject("method").get("twist"));

        beer.setMethod(method);


        JsonObject receivedIngredients = receivedObject.getJsonObject("ingredients");
        beer.ingredients = IngredientFactory.buildIngredient(receivedIngredients);


        beer.setYeast(receivedIngredients.getString("yeast"));

        JsonArray foodPairing = receivedObject.getJsonArray("food_pairing");
        if (foodPairing != null) {
            for (int i = 0 ; i < foodPairing.size() ; i++) {
                beer.foodPairing.add(foodPairing.getString(i));
            }
        }

        beer.setBrewersTips(receivedObject.getString("brewers_tips"));

        beer.setContributedBy(receivedObject.getString("contributed_by"));



        return beer;

    }
}

/*

            //private List<String> foodPairing = null;
            private String brewersTips;
            private String contributedBy;

*/