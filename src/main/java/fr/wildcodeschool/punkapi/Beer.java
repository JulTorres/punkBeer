
package fr.wildcodeschool.punkapi;

import java.util.ArrayList;
import java.util.List;

public class Beer {

    private Integer id;
    private String name;
    private String tagline;
    private String firstBrewed;
    private String description;
    private String imageUrl;
    private Double abv;
    private Double ibu;
    private Double targetFg;
    private Double targetOg;
    private Double ebc;
    private Double srm;
    private Double ph;
    private Double attenuationLevel;
    private Volume volume;
    private BoilVolume boilVolume;
    private Method method;
    private String brewersTips;
    private String contributedBy;
    private String yeast;

    private List<Ingredient> ingredients;
    private List<String> foodPairing = new ArrayList<String>();


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() { return id; }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public void setIbu(Double ibu) {
        this.ibu = ibu;
    }

    public void setTargetFg(Double targetFg) {
        this.targetFg = targetFg;
    }

    public void setTargetOg(Double targetOg) {
        this.targetOg = targetOg;
    }

    public void setEbc(Double ebc) {
        this.ebc = ebc;
    }

    public void setSrm(Double srm) {
        this.srm = srm;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public void setAttenuationLevel(Double attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public void setBoilVolume(BoilVolume boilVolume) {
        this.boilVolume = boilVolume;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setYeast(String yeast) { this.yeast = yeast; }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
