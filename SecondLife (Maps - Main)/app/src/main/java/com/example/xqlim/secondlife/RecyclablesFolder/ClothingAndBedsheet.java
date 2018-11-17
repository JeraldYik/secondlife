package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

/**
 * Entity class that extends Recyclable that represents Clothing and Bedsheet
 */

public class ClothingAndBedsheet extends Recyclable {
    public ClothingAndBedsheet(double quantity, String unit) {
        super(quantity, unit);
        name = "Clothing and Bedsheet";
        imageAssetSmall = R.drawable.small_clothing;
    }
}
