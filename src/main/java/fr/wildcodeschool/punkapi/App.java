package fr.wildcodeschool.punkapi;

import java.util.List;

/**
 * PunkBeer
 * Simple app for communicating with the Punk API
 * By julienTorres
 *
 */
public class App 
{
    public static void main(String[] args) {

        //List<Beer> allBeers;

        Beer myBeer = BeerUtils.getBeerBy(1);
        System.out.println(myBeer.getName());

    }
}
