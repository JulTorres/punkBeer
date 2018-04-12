
package fr.wildcodeschool.punkapi;

import java.util.List;

public class Beer {

    public Integer id;
    public String name;
    public String tagline;
    public String firstBrewed;
    public String description;
    public String imageUrl;
    public Double abv;
    public Double ibu;
    public Double targetFg;
    public Double targetOg;
    public Double ebc;
    public Double srm;
    public Double ph;
    public Double attenuationLevel;
    public Volume volume;
    public BoilVolume boilVolume;
    public Method method;
    public Ingredients ingredients;
    public List<String> foodPairing = null;
    public String brewersTips;
    public String contributedBy;

}
