package com.example.xqlim.secondlife.RecyclablesFolder;


/**
 * Entity Class that stores information of recyclable items
 * Parent class of all other classes in RecyclablesFolder
 */
public class Recyclable {

    protected String name;
    protected double quantity;
    protected String unit;
    protected int price;
    protected String location;
    protected int imageAssetSmall;
    protected int imageAssetLarge;

    /**
     * Requirements to be met before item can be recycled
     */
    protected int recyclableRequirements;

    public Recyclable (double quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    /**
     * Converts quantity to weight
     * @return weight as a string
     */
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
