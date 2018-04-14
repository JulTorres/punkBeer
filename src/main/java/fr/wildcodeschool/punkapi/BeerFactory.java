package fr.wildcodeschool.punkapi;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class BeerFactory {


    public static Beer buildBeer(JsonObject receivedObject) {
        
        Beer beer = new Beer();
/*
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
*/

/*
Refactoring, protect against null !!
factoriser les chemins
 */


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

        JsonObject boilVolumeObject = receivedObject.getJsonObject("boil_volume");
        beer.setBoilVolume(new BoilVolume(
                boilVolumeObject.getInt("value"),
                boilVolumeObject.getString("unit")
        ));


        // METHOD
        JsonObject methodObject = receivedObject.getJsonObject("method");

        // MASH_TEMPS
        List<MashTemp> mashTemps = new ArrayList<>();


        JsonArray mashTempObjects = methodObject.getJsonArray("mash_temp");
        Temp mashTempTemp = new Temp();
        for (int i = 0 ; i < mashTempObjects.size() ; i++) {
            JsonObject mashTempObject = mashTempObjects.getJsonObject(i);
            JsonObject mashTempTempObject = mashTempObject.getJsonObject("temp");
            mashTempTemp.setValue(mashTempTempObject.getJsonNumber("value").doubleValue());
            mashTempTemp.setUnit(mashTempTempObject.getString("unit"));

            int duration = mashTempObject.isNull("duration") ? 0 : mashTempObject.getInt("duration");

            MashTemp mashTemp = new MashTemp(
                    mashTempTemp,
                    duration
            );

            mashTemps.add(mashTemp);
        }


        JsonObject fermentationTempObject = methodObject.getJsonObject("fermentation").getJsonObject("temp");
        Temp fermentationTemp = new Temp();

        fermentationTemp.setValue(fermentationTempObject.getJsonNumber("value").doubleValue());
        fermentationTemp.setUnit(fermentationTempObject.getString("unit"));

        Fermentation fermentation = new Fermentation(fermentationTemp);


        Method method = new Method(mashTemps, fermentation, methodObject.get("twist"));

        beer.setMethod(method);



        // INGREDIENTS
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

    public static List<Beer> buildBeerList(JsonArray received) {
        List<Beer> beers = new ArrayList<Beer>();

        for (int i = 0 ; i < received.size() ; i++) {
            beers.add(BeerFactory.buildBeer(received.getJsonObject(1)));
        }

        return beers;
    }
}

/*

            //private List<String> foodPairing = null;
            private String brewersTips;
            private String contributedBy;

*/