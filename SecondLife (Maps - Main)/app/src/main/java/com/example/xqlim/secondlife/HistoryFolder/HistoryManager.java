package com.example.xqlim.secondlife.HistoryFolder;

import android.util.Log;

import com.example.xqlim.secondlife.RecyclablesFolder.AluminiumDrinkCan;
import com.example.xqlim.secondlife.RecyclablesFolder.Glass;
import com.example.xqlim.secondlife.RecyclablesFolder.MetalTin;
import com.example.xqlim.secondlife.RecyclablesFolder.Paper;
import com.example.xqlim.secondlife.RecyclablesFolder.Plastic;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.RecyclablesFolder.SmallElectricalAppliance;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Control class to manage History items
 * History items are Recyclable Objects that have been recycled
 */

public class HistoryManager {

    /**
     * TAG for logging purposes
     */

    private static final String TAG = "HistoryManagerTAG";

    /**
     * ArrayList of History Items
     */

    private ArrayList<Recyclable> recycledItems;

    /**
     * private method to instantiate HistoryManager
     */

    private static HistoryManager instance = new HistoryManager();

    /**
     * History Adapter
     */

    private HistAdapter mAdapter;

    /**
     * Private constructor for History Manager for Singleton Design Pattern
     */
    private HistoryManager(){
        this.recycledItems = new ArrayList<>();
        initializeData();
    }

    /**
     * public method to instantiate HistoryManager
     * @return returns the History Manager
     */

    public static HistoryManager getInstance(){
        return instance;
    }

    /**
     * Initialises the History Items for the page
     */
    public void initializeData() {
        recycledItems.add(new Plastic(4.9, "kg"));
        recycledItems.add(new Paper(3.7, "kg"));
        recycledItems.add(new Glass(2.4, "kg"));
        recycledItems.add(new MetalTin(5, "tins"));
        recycledItems.add(new SmallElectricalAppliance(4.6, "kg"));
        recycledItems.add(new AluminiumDrinkCan(12, "kg"));
    }

    /**
     * Add History Item
     * @param recyclable History item to be added
     */
    public void addHist(Recyclable recyclable) {

        Boolean match = false;
        int index = 999;
        for (int i = 0; i<recycledItems.size(); i++){
            Log.d(TAG, "Name: " + recycledItems.get(i).getName());


            if (recycledItems.get(i).getName() == recyclable.getName()){
                index = i;
                match = true;
                Log.d(TAG, "match! name is + " + recyclable.getName());
                break;
            }
        }

        if (match) {
            Log.d(TAG, "match, replacing qty");
            Recyclable recyclable_exist = recycledItems.get(index);
            recyclable_exist.setQuantity(recyclable_exist.getQuantity() + recyclable.getQuantity());
        }
        else {
            Log.d(TAG, recyclable.getName() + "no match, adding new");
            index = recycledItems.size();
            recycledItems.add(index, recyclable);
        }
    }

    /**
     * @return ArrayList of History items
     */

    public ArrayList<Recyclable> getRecycledItems() {
        return recycledItems;
    }
}
