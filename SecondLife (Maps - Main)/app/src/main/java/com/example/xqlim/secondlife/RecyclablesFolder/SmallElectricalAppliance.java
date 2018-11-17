package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

/**
 * Entity class that extends Recyclable that represents small electrical appliances
 */


public class SmallElectricalAppliance extends Recyclable {
    public SmallElectricalAppliance(double quantity, String unit) {
        super(quantity, unit);
        name = "Electronics and Appliances";
        imageAssetSmall = R.drawable.small_appliance;
    }
}
