package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

public class EWaste extends Recyclable {
    public EWaste(double quantity, String unit) {
        super(quantity, unit);
        name = "Electronic Waste";
        imageAssetSmall = R.drawable.small_ewaste;

    }
}
