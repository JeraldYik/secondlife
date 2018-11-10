package com.example.xqlim.secondlife.RecyclablesFolder;

import com.example.xqlim.secondlife.R;

public class Paper extends Recyclable {
    public Paper(double quantity, String unit) {
        super(quantity, unit);
        name = "Paper";
        imageAssetSmall = R.drawable.small_paper;
/*        imageAssetLarge = R.drawable.large_paper;
        recyclableRequirements = R.string.paper_requirements;*/
    }
}
