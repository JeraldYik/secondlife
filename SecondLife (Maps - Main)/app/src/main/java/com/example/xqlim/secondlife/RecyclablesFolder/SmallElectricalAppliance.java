package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

public class SmallElectricalAppliance extends Recyclable {
    public SmallElectricalAppliance(double quantity, String unit) {
        super(quantity, unit);
        name = "Electronics and Appliances";
        imageAssetSmall = R.drawable.small_appliance;
    }
}
