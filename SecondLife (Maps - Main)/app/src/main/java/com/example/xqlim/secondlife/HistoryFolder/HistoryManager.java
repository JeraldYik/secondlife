package com.example.xqlim.secondlife.HistoryFolder;

import com.example.xqlim.secondlife.RecyclablesFolder.AluminiumDrinkCan;
import com.example.xqlim.secondlife.RecyclablesFolder.Glass;
import com.example.xqlim.secondlife.RecyclablesFolder.MetalTin;
import com.example.xqlim.secondlife.RecyclablesFolder.Paper;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.RecyclablesFolder.SmallElectricalAppliance;

import java.util.ArrayList;

public class HistoryManager {

    private ArrayList<Recyclable> recycledItems;

    private static HistoryManager instance = new HistoryManager();

    private HistoryManager(){}

    public static HistoryManager getInstance(){
        return instance;
    }

    public void addHist(Recyclable recyclable) {
        recycledItems.add(recyclable);
    }

    public ArrayList<Recyclable> getRecycledItems() {
        return recycledItems;
    }
}
