package fr.wildcodeschool.punkapi;

import java.util.ArrayList;
import java.util.List;

import static fr.wildcodeschool.punkapi.BeerUtils.*;

/**
 * PunkBeer
 * Simple app for communicating with the Punk API
 * By julienTorres
 *
 */
public class App 
{
    public static void main(String[] args) {

        List<Beer> allBeers = getAllBeers();
        System.out.println(allBeers.get(3).getName());



/*        Beer myBeer = BeerUtils.getBeerBy(1);
        System.out.println(myBeer.getName());*/

    }
}
