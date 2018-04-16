package fr.wildcodeschool.punkapi;

import static fr.wildcodeschool.punkapi.BeerUtils.*;
import java.util.List;

/**
 * PunkBeer
 * Simple app for communicating with the Punk API
 * By julienTorres
 *
 */
public class App 
{
    public static void main(String... args) {

/*        getAllBeers();
        for (int i = 1 ; i < beers.size() ; i++) {
            System.out.println(beers.get(i).getName());
        }

        getBeerBy(6);
        System.out.println(beers.get(0).getAbv());
*/
       /*
         * getting a list of beers by characteristics
         *//*
        getBeerBy("abv", true, 16);
        for (int i = 1; i < beers.size(); i++) {
            System.out.println(beers.get(i).getId());
        }

        getBeerBy("india");
        for (int i = 1; i < beers.size(); i++) {
            System.out.println(beers.get(i).getName());
            System.out.println(beers.get(i).getId());
        }*/

        List<Beer> customList = getBeerByIngredient("Extra Pale", true, 6);
        for (int i = 1; i < customList.size(); i++) {
            System.out.println(customList.get(i).getName());
            System.out.println(customList.get(i).getId());
        }
        System.out.println("La liste contient " + customList.size() + " bières.");

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