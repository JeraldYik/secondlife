package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

/**
 * Entity class that extends Recyclable that represents Aluminium Drink Can
 */


public class AluminiumDrinkCan extends Recyclable {
    public AluminiumDrinkCan(double quantity, String unit) {
        super(quantity, unit);
        location = "Recycle At: Cash 4 Trash Centres, Recycling Bins";
        name = "Aluminum Cans";
        recyclableRequirements = R.string.plastics_requirements;
        imageAssetSmall = R.drawable.small_cans;
        imageAssetLarge = R.drawable.large_cans;
    }
}
