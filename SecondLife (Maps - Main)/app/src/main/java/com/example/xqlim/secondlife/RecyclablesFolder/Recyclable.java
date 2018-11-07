package com.example.xqlim.secondlife.RecyclablesFolder;

public abstract class Recyclable {

    protected double quantity;
    protected String unit;

    protected int imageAssetSmall;
    protected int imageAssetLarge;
    protected String recyclableRequirements;

    Recyclable (double quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

}
