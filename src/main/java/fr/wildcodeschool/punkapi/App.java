package fr.wildcodeschool.punkapi;

import static fr.wildcodeschool.punkapi.BeerFactory.beers;
import static fr.wildcodeschool.punkapi.BeerUtils.getAllBeers;

/**
 * PunkBeer
 * Simple app for communicating with the Punk API
 * By julienTorres
 *
 */
public class App 
{
    public static void main(String[] args) {

        getAllBeers();
        System.out.println(beers.get(186).getName());

    }
}
