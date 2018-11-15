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

public class HistoryManager {

    private static final String TAG = "HistoryManagerTAG";

    private ArrayList<Recyclable> recycledItems;

    private static HistoryManager instance = new HistoryManager();

    private HistAdapter mAdapter;

    private HistoryManager(){
        this.recycledItems = new ArrayList<>();
        initializeData();
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




//        Iterator<Recyclable> iter = recycledItems.iterator();
//
//        while (iter.hasNext()) {
//            Recyclable re = iter.next();
//            String name = re.getName();
//
//            if (name == recyclable.getName()){
//                re.setQuantity(re.getQuantity()+recyclable.getQuantity());
//            }
//            else{
//
//            }
//            recycledItems.add(re);
//        }

//
//
//        for (Recyclable r : recycledItems) {
//            if (r.getName() == recyclable.getName()) {
//                double qty = r.getQuantity() + recyclable.getQuantity();
//                r.setQuantity(qty);
//                break;
//            }
//            else
//                recycledItems.add(r);
//        }
    }

    public ArrayList<Recyclable> getRecycledItems() {
        return recycledItems;
    }
}
