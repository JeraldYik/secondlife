package com.example.xqlim.secondlife.FavouritesFolder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xqlim.secondlife.R;

import java.util.ArrayList;


public class FavouritesManager extends AppCompatActivity {
    private ArrayList<Favourite> favouriteLocations;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
    }

    public void markFavourites (ArrayList favouriteLocations){

    }
}
