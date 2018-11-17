package com.example.xqlim.secondlife.FavouritesFolder;

import com.example.xqlim.secondlife.MapsFolder.Location;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Control class to store and manage Favourites
 */

public class FavouritesManager {
    /**
     * ArrayList of locations that have been favourited
     */
    private ArrayList<Location> favouriteList;

    /**
     * Constructor for favouritesManager that intialises favouriteList
     */
    public FavouritesManager() {
        this.favouriteList = new ArrayList<>();
    }

    /**
     * @return favouriteList
     */
    public ArrayList<Location> viewFavourites() {
        return this.favouriteList;
    }

    /**
     * Adds a location to the favourite list
     * @param location location to be added
     */

    public void addFavourite(Location location) {
        this.favouriteList.add(location);
    }

    /**
     * Deletes location from the favourite list
     * @param location location to be deleted
     * @return Boolean describing if
     */

    public boolean deleteFavourite(Location location) {
        for(Location l : favouriteList) {
            if(l.equals(location)) {
                this.favouriteList.remove(l);
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        String str = "";
        int counter = 1;
        for(Location l : this.favouriteList) {
            str += (counter + ". " + l.getLatLng().toString() + "\n");
            counter++;
        }
        return str;
    }

    public ArrayList<Location> getFavouriteList() {
        return favouriteList;
    }


}
