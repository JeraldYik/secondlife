package com.example.xqlim.secondlife.RecycleFolder;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.AluminiumDrinkCan;
import com.example.xqlim.secondlife.RecyclablesFolder.ClothingAndBedsheet;
import com.example.xqlim.secondlife.RecyclablesFolder.CorrugatedCardboard;
import com.example.xqlim.secondlife.RecyclablesFolder.EWaste;
import com.example.xqlim.secondlife.RecyclablesFolder.Glass;
import com.example.xqlim.secondlife.RecyclablesFolder.MetalTin;
import com.example.xqlim.secondlife.RecyclablesFolder.Paper;
import com.example.xqlim.secondlife.RecyclablesFolder.Plastic;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.RecyclablesFolder.SecondHandItem;
import com.example.xqlim.secondlife.RecyclablesFolder.SmallElectricalAppliance;

import java.util.ListIterator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * Class to manage all available types of recyclables
 */
public class RecyclableList extends AppCompatActivity{

    private static final String TAG = "RecyclableListTAG";
    private LinearLayoutManager lLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclable_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Choose category ");
        Log.d(TAG, "On create");

        Log.d(TAG, String.valueOf(getAllItemList()==null));
        List<Recyclable> rowListItem = getAllItemList();
        Log.v(TAG,"list has been added" );

        lLayout = new LinearLayoutManager(RecyclableList.this);

        RecyclerView rView = findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclableImageAdapter rcAdapter = new RecyclableImageAdapter(this, rowListItem);

//        for (Recyclable re : rowListItem){
//            Log.d(TAG, re.getName());
//        }

        rView.setAdapter(rcAdapter);
    }

    private List<Recyclable> getAllItemList(){

        List<Recyclable> allItems = new ArrayList<>();
        allItems.add(new Plastic(0, null));
        allItems.add(new Paper(0, null));
        allItems.add(new MetalTin(0, null));
        allItems.add(new AluminiumDrinkCan(0, null));
        allItems.add(new CorrugatedCardboard(0, null));
        allItems.add(new Glass(0, null));
        allItems.add(new EWaste(0, null));
        allItems.add(new SecondHandItem(0, null));
        allItems.add(new SmallElectricalAppliance(0, null));
        allItems.add(new ClothingAndBedsheet(0, null));

        return allItems;
    }
}
