package com.example.xqlim.secondlife.MapsFolder;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private static final String TAG = "LocationCreator";
    private String Name;
    private LatLng latLng;


    private int openingTime;
    private int closingTime;
    private boolean isFavourite;


    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    private String Description;
    private String AddressUnitNumber;
    private String AddressStreetName;
    private String AddressPostalCode;
    private String AddressBuildingName;
    private String AddressBlockNumber;

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getAddressUnitNumber() {
        return AddressUnitNumber;
    }

    public String getAddressStreetName() {
        return AddressStreetName;
    }

    public String getAddressPostalCode() {
        return AddressPostalCode;
    }

    public String getAddressBuildingName() {
        return AddressBuildingName;
    }

    public String getAddressBlockNumber() {
        return AddressBlockNumber;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {

        String[] split = description.split("\\s+");

        StringBuilder builder = new StringBuilder();
        for(int i = 5; i < split.length; i++) {
            builder.append(split[i]).append(" ");
        }
        String str = builder.toString();
        Log.d(TAG, str);


        Description = str;
    }

    public void setAddressUnitNumber(String addressUnitNumber) {
        AddressUnitNumber = addressUnitNumber;
    }

    public void setAddressStreetName(String addressStreetName) {
        AddressStreetName = addressStreetName;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        AddressPostalCode = addressPostalCode;
    }

    public void setAddressBuildingName(String addressBuildingName) {
        AddressBuildingName = addressBuildingName;
    }

    public void setAddressBlockNumber(String addressBlockNumber) {
        AddressBlockNumber = addressBlockNumber;
    }
}
