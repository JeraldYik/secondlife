package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

/**
 * Entity class that extends Recyclable that represents second hand items
 */


public class SecondHandItem extends Recyclable {
    public SecondHandItem(double quantity, String unit) {
        super(quantity, unit);
        name = "Second-Hand Items";
        imageAssetSmall = R.drawable.small_second_hand;
    }
}
