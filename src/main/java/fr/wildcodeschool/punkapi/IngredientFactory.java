package fr.wildcodeschool.punkapi;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class IngredientFactory {

    static List<Ingredient> ingredients = new ArrayList<Ingredient>();

    public static List<Ingredient> buildIngredient(JsonObject receivedIngredients) {

        JsonArray hops = receivedIngredients.getJsonArray("hops");
        if( hops != null) {
            for(int i = 0 ; i < hops.size() ; i++) {
                Ingredient ingredient = new Ingredient();

                ingredient.setIngredientType("hops");
                ingredient.setName(hops.getJsonObject(i).getString("name"));
                ingredient.setAmountUnit(hops.getJsonObject(i).getJsonObject("amount").getString("unit"));
                ingredient.setAmountValue(hops.getJsonObject(i).getJsonObject("amount").getJsonNumber("value").doubleValue());
                ingredient.setAdd(hops.getJsonObject(i).getString("add"));
                ingredient.setAttribute(hops.getJsonObject(i).getString("attribute"));

                ingredients.add(ingredient);
            }

        }

        return ingredients;
    }

}
