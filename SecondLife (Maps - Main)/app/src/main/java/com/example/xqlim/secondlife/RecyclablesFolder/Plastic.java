package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

public class Plastic extends Recyclable {

    public Plastic(double quantity, String unit) {
        super(quantity, unit);
        name = "Plastic";
        price = 1;
        imageAssetSmall = R.drawable.small_plastic;
        imageAssetLarge = R.drawable.large_plastic;
        recyclableRequirements = R.string.plastics_requirements;
    }
}
