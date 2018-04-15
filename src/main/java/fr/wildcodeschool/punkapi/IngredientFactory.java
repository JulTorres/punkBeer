package fr.wildcodeschool.punkapi;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static fr.wildcodeschool.punkapi.IngredientType.HOPS;
import static fr.wildcodeschool.punkapi.IngredientType.MALT;

public class IngredientFactory {


    public static List<Ingredient> buildIngredientList(JsonObject receivedIngredients) {//TODO factoriser

        List<Ingredient> ingredients = new ArrayList<>();

        JsonArray hops = receivedIngredients.getJsonArray("hops");
        if( hops != null) {
            for(int i = 0 ; i < hops.size() ; i++) {
                Ingredient ingredient = new Ingredient();

                ingredient.setIngredientType(HOPS);

                String name = hops.getJsonObject(i).isNull("name") ? "" : hops.getJsonObject(i).getString("name");
                ingredient.setName(name);

                String unit = hops.getJsonObject(i).getJsonObject("amount").isNull("unit") ? "" : hops.getJsonObject(i).getJsonObject("amount").getString("unit");
                ingredient.setAmountUnit(unit);

                double value = hops.getJsonObject(i).getJsonObject("amount").isNull("value") ? 0 : hops.getJsonObject(i).getJsonObject("amount").getJsonNumber("value").doubleValue();
                ingredient.setAmountValue(value);

                String add = hops.getJsonObject(i).isNull("add") ? "" : hops.getJsonObject(i).getString("add");
                ingredient.setAdd(add);

                String attribute = hops.getJsonObject(i).isNull("attribute") ? "" : hops.getJsonObject(i).getString("attribute");
                ingredient.setAttribute(attribute);

                ingredients.add(ingredient);
            }

        }

        JsonArray malts = receivedIngredients.getJsonArray("malt");
        if (malts != null) {
            for (int i = 0 ; i < malts.size() ; i++) {
                Ingredient ingredient = new Ingredient();

                ingredient.setIngredientType(MALT);

                String name = malts.getJsonObject(i).isNull("name") ? "" : malts.getJsonObject(i).getString("name");
                ingredient.setName(name);

                JsonObject amount = malts.getJsonObject(i).isNull("amount") ? null : malts.getJsonObject(i).getJsonObject("amount");
                if (amount != null) {
                    String unit = amount.isNull("unit") ? "" : amount.getString("unit");
                    ingredient.setAmountUnit(unit);

                    double value = amount.isNull("value") ? 0 : amount.getJsonNumber("value").doubleValue();
                    ingredient.setAmountValue(value);
                }

                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

}
