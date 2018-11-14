package com.example.xqlim.secondlife.FavouritesFolder;

import com.example.xqlim.secondlife.MapsFolder.Location;

import java.util.ArrayList;
import java.util.Iterator;


public class FavouritesManager {
    private ArrayList<Location> favouriteList;

    public FavouritesManager() {
        this.favouriteList = new ArrayList<>();
    }

    public ArrayList<Location> viewFavourites() {
        return this.favouriteList;
    }

    public void addFavourite(Location location) {
        this.favouriteList.add(location);
    }

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
