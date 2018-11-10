package com.example.xqlim.secondlife.RecyclablesFolder;

import java.util.ArrayList;
import java.util.List;

public class Recyclable {

    protected String name;
    protected double quantity;
    protected String unit;
    private int photo;

    protected int imageAssetSmall;
    protected int imageAssetLarge;
    protected String recyclableRequirements;

    public Recyclable (double quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public Recyclable(String name, int photo){
        this.name = name;
        this.photo = photo;
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
        String qtyDisp;
        if (unit != "kg") {
            qtyDisp = (Integer.toString((int) quantity) + " " + unit);
        }
        else {
            qtyDisp = (Double.toString(quantity) + " " + unit);
        }
        return qtyDisp;
    }

/*    public List<Recyclable> getListofRecyclables(){

        List<Recyclable> categories = new ArrayList<>();
        categories.add()

    }*/

}
