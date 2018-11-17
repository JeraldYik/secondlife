package com.example.xqlim.secondlife.RecycleFolder;

import android.util.Log;

import com.example.xqlim.secondlife.RecyclablesFolder.Glass;
import com.example.xqlim.secondlife.RecyclablesFolder.Paper;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;

import java.util.ArrayList;

/**
 * Control class that manages Recyclables
 */

public class RecycleManager {

    private static final String TAG = "RecycleManagerTAG";

    private ArrayList<Recyclable> recycledItems;

    private static RecycleManager instance = new RecycleManager();

    private RecycleManager(){
        this.recycledItems = new ArrayList<>();
    }

    public static RecycleManager getInstance(){
        return instance;
    }

//     Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    // NOT USED
    public void initializeData() {
        this.recycledItems.add(new Paper(3.7, "kg"));
        this.recycledItems.add(new Glass(2.4, "kg"));
    }

    /**
     * Adds recyclable to list
     * @param recyclable
     */
    public void addToList(Recyclable recyclable) {
        Log.i(TAG, this.recycledItems.toString());
        this.recycledItems.add(recyclable);
    }

    public ArrayList<Recyclable> getRecycledItems() {
        return recycledItems;
    }
}
