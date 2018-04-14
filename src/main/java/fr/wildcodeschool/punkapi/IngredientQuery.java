package fr.wildcodeschool.punkapi;

public enum IngredientQuery {

    ABV ("abv"),
    IBU ("ibu"),
    EBC ("ebc"),
    YEAST ("yeast");


    private final String name;

    IngredientQuery(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
