package com.example.xqlim.secondlife.RecyclablesFolder;

public class Recyclable {

    protected String name;
    protected double quantity;
    protected String unit;
    protected int price;
    private int photo;

    protected int imageAssetSmall;
    protected int imageAssetLarge;
    protected int recyclableRequirements;

    public Recyclable(){};

    public Recyclable(String name, int image){};

    public Recyclable (double quantity, String unit) {
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

    public void setPrice(int price) {this.price = price; };

    public int getImageAssetSmall() {
        return imageAssetSmall;
    }

    public int getImageAssetLarge() {
        return imageAssetLarge;
    }

    public int getPrice(){ return price; }

    public int getRecyclableRequirements() {
        return recyclableRequirements;
    }

    public String getQtyDisplay() {
        String qtyDisp;
        if (unit != "kg") {
            qtyDisp = (Integer.toString((int) quantity) + " " + unit);
        }
        else {
            qtyDisp = (Double.toString(quantity) + " " + unit);
        }
        return qtyDisp;
    }

}
