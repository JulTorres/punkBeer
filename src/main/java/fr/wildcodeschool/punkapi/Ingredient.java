
package fr.wildcodeschool.punkapi;

import java.util.List;

public class Ingredient {

    public IngredientType ingredientType;//TODO remplacer par enum
    public String name;
    public double amountValue;
    public String amountUnit;
    public String add;
    public String attribute;


    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAmountValue(double amountValue) {
        this.amountValue = amountValue;
    }

    public double getAmountValue() {
        return amountValue;
    }

    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
