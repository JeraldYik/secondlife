package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

/**
 * Entity class that extends Recyclable that represents Glass
 */


public class Glass extends Recyclable {
    public Glass(double quantity, String unit) {
        super(quantity, unit);
        name = "Glass";
        imageAssetSmall = R.drawable.small_glass;
    }
}
