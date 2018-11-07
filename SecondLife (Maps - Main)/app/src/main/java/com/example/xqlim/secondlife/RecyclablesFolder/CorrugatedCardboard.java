package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

public class CorrugatedCardboard extends Recyclable {
    public CorrugatedCardboard(double quantity, String unit) {
        super(quantity, unit);
        name = "Corrugated Cardboard";
        imageAssetSmall = R.drawable.small_corrugated_cardboard;
    }
}
