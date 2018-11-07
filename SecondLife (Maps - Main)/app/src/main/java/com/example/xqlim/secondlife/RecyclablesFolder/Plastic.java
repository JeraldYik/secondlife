package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

public class Plastic extends Recyclable {
    public Plastic(double quantity, String unit) {
        super(quantity, unit);
        name = "Plastic";
        imageAssetSmall = R.drawable.small_plastic;
    }
}
