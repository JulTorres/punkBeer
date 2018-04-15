package fr.wildcodeschool.punkapi;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static fr.wildcodeschool.punkapi.BeerUtils.beers;

public class BeerFactory {

    //public static List<Beer> beers = new ArrayList<Beer>();

    public static Beer buildBeer(JsonObject receivedObject) {

        Beer beer = new Beer();
/*
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
*/

/*
Refactoring, protect against null !!
 */

        int id = receivedObject.isNull("id") ? 0 : receivedObject.getInt("id");
        beer.setId(id);

        String name = receivedObject.isNull("name") ? "" : receivedObject.getString("name");
        beer.setName(name);

        String description = receivedObject.isNull("description") ? "" : receivedObject.getString("description");
        beer.setDescription(description);

        String imageUrl = receivedObject.isNull("image_url") ? "" : receivedObject.getString("image_url");
        beer.setImageUrl(imageUrl);

        double abv = receivedObject.isNull("abv") ? 0 : receivedObject.getJsonNumber("abv").doubleValue();
        beer.setAbv(abv);

        double ibu = receivedObject.isNull("ibu") ? 0 : receivedObject.getJsonNumber("ibu").doubleValue();
        beer.setIbu(ibu);

        double targetFg = receivedObject.isNull("target_fg") ? 0 : receivedObject.getJsonNumber("target_fg").doubleValue();
        beer.setTargetFg(targetFg);

        double targetOg = receivedObject.isNull("target_og") ? 0 : receivedObject.getJsonNumber("target_og").doubleValue();
        beer.setTargetOg(targetOg);

        double ebc = receivedObject.isNull("ebc") ? 0 : receivedObject.getJsonNumber("ebc").doubleValue();
        beer.setEbc(ebc);

        double srm = receivedObject.isNull("srm") ? 0 : receivedObject.getJsonNumber("srm").doubleValue();
        beer.setSrm(srm);

        double ph = receivedObject.isNull("ph") ? 0 : receivedObject.getJsonNumber("ph").doubleValue();
        beer.setPh(ph);

        double attenuationLevel = receivedObject.isNull("attenuation_level") ? 0 : receivedObject.getJsonNumber("attenuation_level").doubleValue();
        beer.setAttenuationLevel(attenuationLevel);


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

            double mashTempTempValueObject = mashTempTempObject.isNull("value") ? 0 : mashTempTempObject.getJsonNumber("value").doubleValue();
            mashTempTemp.setValue(mashTempTempValueObject);

            String mashTempTempUnitObject = mashTempTempObject.isNull("unit") ? "" : mashTempTempObject.getString("unit");
            mashTempTemp.setUnit(mashTempTempUnitObject);

            int duration = mashTempObject.isNull("duration") ? 0 : mashTempObject.getInt("duration");

            MashTemp mashTemp = new MashTemp(
                    mashTempTemp,
                    duration
            );

            mashTemps.add(mashTemp);
        }


        JsonObject fermentationTempObject = methodObject.getJsonObject("fermentation").getJsonObject("temp");
        Temp fermentationTemp = new Temp();

        double fermentationTempValue = fermentationTempObject.isNull("value") ? 0 : fermentationTempObject.getJsonNumber("value").doubleValue();
        fermentationTemp.setValue(fermentationTempValue);

        String fermentationTempUnit = fermentationTempObject.isNull("unit") ? "" : fermentationTempObject.getString("unit");
        fermentationTemp.setUnit(fermentationTempUnit);

        Fermentation fermentation = new Fermentation(fermentationTemp);


        Method method = new Method(mashTemps, fermentation, methodObject.get("twist"));

        beer.setMethod(method);



        // INGREDIENTS                                              
        JsonObject receivedIngredients = receivedObject.getJsonObject("ingredients");
        beer.ingredients = IngredientFactory.buildIngredient(receivedIngredients);

        String yeast = receivedIngredients.isNull("yeast") ? "" : receivedIngredients.getString("yeast");
        beer.setYeast(yeast);

        JsonArray foodPairing = receivedObject.getJsonArray("food_pairing");
        if (foodPairing != null) {
            for (int i = 0 ; i < foodPairing.size() ; i++) {
                beer.foodPairing.add(foodPairing.getString(i));
            }
        }

        String brewersTips = receivedObject.isNull("brewers_tips") ? "" : receivedObject.getString("brewers_tips");
        beer.setBrewersTips(brewersTips);

        String contributedBy = receivedObject.isNull("contributed_by") ? "" : receivedObject.getString("contributed_by");
        beer.setContributedBy(contributedBy);


        return beer;

    }

    public static void buildBeerList(JsonArray received) {

        for (int i = 0 ; i < received.size() ; i++) {
            beers.add(BeerFactory.buildBeer(received.getJsonObject(i)));
        }

        return;
    }
}