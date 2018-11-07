package com.example.xqlim.secondlife.RecyclablesFolder;

public abstract class Recyclable {

    protected String name;
    protected double quantity;
    protected String unit;

    protected int imageAssetSmall;
    protected int imageAssetLarge;
    protected String recyclableRequirements;

    Recyclable (double quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getImageAssetSmall() {
        return imageAssetSmall;
    }

    public int getImageAssetLarge() {
        return imageAssetLarge;
    }

    public String getRecyclableRequirements() {
        return recyclableRequirements;
    }

    public String getQtyDisplay() {

        String qtyDisp = (Double.toString(quantity) + " " + unit);
        return qtyDisp;
    }

}
