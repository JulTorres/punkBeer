package fr.wildcodeschool.punkapi;

import java.util.ArrayList;
import java.util.List;

import static fr.wildcodeschool.punkapi.BeerUtils.beers;
import static fr.wildcodeschool.punkapi.BeerUtils.getAllBeers;
import static fr.wildcodeschool.punkapi.BeerUtils.getBeerBy;

/**
 * PunkBeer
 * Simple app for communicating with the Punk API
 * By julienTorres
 *
 */
public class App 
{
    public static void main(String... args) {

        getAllBeers();
        for (int i = 1 ; i < beers.size() ; i++) {
            System.out.println(beers.get(i).getName());
        }
        getBeerBy(6);
        System.out.println(beers.get(0).getAbv());

        getBeerBy(IngredientQuery.ABV, true, 16);
        for (int i = 1; i < beers.size(); i++) {
            System.out.println(beers.get(i).getId());
        }

        getBeerBy("india");
        for (int i = 1; i < beers.size(); i++) {
            System.out.println(beers.get(i).getName());
            System.out.println(beers.get(i).getId());
        }

    }
}

/*
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Planet <earth_weight>");
            System.exit(-1);
        }
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight/EARTH.surfaceGravity();
        for (Planet p : Planet.values())
           System.out.printf("Your weight on %s is %f%n",
                             p, p.surfaceWeight(mass));
    }
 */