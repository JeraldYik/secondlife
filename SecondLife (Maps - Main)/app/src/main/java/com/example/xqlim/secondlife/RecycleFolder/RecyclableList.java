package com.example.xqlim.secondlife.RecycleFolder;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import java.util.ListIterator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;



public class RecyclableList extends AppCompatActivity{

    private GridLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclable_list);

        List<Recyclable> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(RecyclableList.this, 2);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclableImageAdapter rcAdapter = new RecyclableImageAdapter(RecyclableList.this, rowListItem);
        rView.setAdapter(rcAdapter);

    }

    private List<Recyclable> getAllItemList(){

        List<Recyclable> allItems = new ArrayList<Recyclable>();
        allItems.add(new Recyclable("Plastics", R.drawable.small_plastic));
        allItems.add(new Recyclable("Paper", R.drawable.small_paper));
        allItems.add(new Recyclable("Metal Tins", R.drawable.small_metal_tin));
        allItems.add(new Recyclable("Metal Cans", R.drawable.small_cans));
        allItems.add(new Recyclable("Corrugated Cardboard", R.drawable.small_corrugated_cardboard));
        allItems.add(new Recyclable("Clothes and Beddings", R.drawable.small_clothing));
        allItems.add(new Recyclable("Glass", R.drawable.small_glass));
        allItems.add(new Recyclable("E-Waste", R.drawable.small_ewaste));
        allItems.add(new Recyclable("Second-Hand Items", R.drawable.small_second_hand));
        allItems.add(new Recyclable("Small Electrical Appliances", R.drawable.small_appliance));

        return allItems;
    }
}
