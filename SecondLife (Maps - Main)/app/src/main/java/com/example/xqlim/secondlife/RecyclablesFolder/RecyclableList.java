package com.example.xqlim.secondlife.RecyclablesFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.xqlim.secondlife.R;

public class RecyclableList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclable_list);

        GridView gridView = findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class

        gridView.setAdapter(new RecyclableImageAdapter(this));

       /* gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), RecyclableFragment.class);
                // passing array index
                startActivity(i);
            }
        });*/
    }
}
