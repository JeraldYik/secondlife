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

public class HistoryManager {

    private static final String TAG = "HistoryManagerTAG";

    private ArrayList<Recyclable> recycledItems;

    private static HistoryManager instance = new HistoryManager();

    private HistAdapter mAdapter;

    private HistoryManager(){
        this.recycledItems = new ArrayList<>();
    }

    public static HistoryManager getInstance(){
        return instance;
    }

    public void initializeData() {
        recycledItems.add(new Plastic(4.9, "kg"));
        recycledItems.add(new Paper(3.7, "kg"));
        recycledItems.add(new Glass(2.4, "kg"));
        recycledItems.add(new MetalTin(5, "tins"));
        recycledItems.add(new SmallElectricalAppliance(4.6, "kg"));
        recycledItems.add(new AluminiumDrinkCan(12, "cans"));
        recycledItems.add(new MetalTin(2, "tins"));
        recycledItems.add(new SmallElectricalAppliance(3.1, "kg"));
    }

    public void addHist(Recyclable recyclable) {
        for (Recyclable r : recycledItems) {
            if (r.getName() == recyclable.getName()) {
                double qty = r.getQuantity() + recyclable.getQuantity();
                r.setQuantity(qty);
                break;
            }
            else
                recycledItems.add(r);
        }
        mAdapter = new HistAdapter(recycledItems);

    }

    public ArrayList<Recyclable> getRecycledItems() {
        return recycledItems;
    }
}
