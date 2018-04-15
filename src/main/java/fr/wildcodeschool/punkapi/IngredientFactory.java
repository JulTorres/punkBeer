package fr.wildcodeschool.punkapi;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static fr.wildcodeschool.punkapi.IngredientType.HOPS;
import static fr.wildcodeschool.punkapi.IngredientType.MALT;

public class IngredientFactory {

    private static List<Ingredient> ingredients = new ArrayList<Ingredient>();

    public static List<Ingredient> buildIngredientList(JsonObject receivedIngredients) {//TODO factoriser

        JsonArray hops = receivedIngredients.getJsonArray("hops");
        if( hops != null) {
            for(int i = 0 ; i < hops.size() ; i++) {
                Ingredient ingredient = new Ingredient();

                ingredient.setIngredientType(HOPS);
                ingredient.setName(hops.getJsonObject(i).getString("name"));
                ingredient.setAmountUnit(hops.getJsonObject(i).getJsonObject("amount").getString("unit"));
                ingredient.setAmountValue(hops.getJsonObject(i).getJsonObject("amount").getJsonNumber("value").doubleValue());
                ingredient.setAdd(hops.getJsonObject(i).getString("add"));
                ingredient.setAttribute(hops.getJsonObject(i).getString("attribute"));

                ingredients.add(ingredient);
            }

        }

        JsonArray malts = receivedIngredients.getJsonArray("malt");
        if (malts != null) {
            for (int i = 0 ; i < malts.size() ; i++) {
                Ingredient ingredient = new Ingredient();

                ingredient.setIngredientType(MALT);
                ingredient.setName(malts.getJsonObject(i).getString("name"));
                ingredient.setAmountUnit(malts.getJsonObject(i).getJsonObject("amount").getString("unit"));
                ingredient.setAmountValue(malts.getJsonObject(i).getJsonObject("amount").getJsonNumber("value").doubleValue());

                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

}
