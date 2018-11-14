package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.xqlim.secondlife.HistoryFolder.HistoryManager;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Plastic;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

public class DisplayRequirements extends AppCompatActivity {

    private static final String TAG = "DisplayRequirementsTag";
    TextView category_name, category_price, category_description;
    ImageView category_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
    Button addButton;
    public static final String INTENT_KEY = "recycle_intent_key";
    public static final int INTENT_VALUE = 100;


//    private int position;
//
//    public void positionClicked() {
//        Intent in = getIntent();
//        Bundle b = in.getExtras();
//        position = b.getInt("position clicked");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_requirements2);

        numberButton = findViewById(R.id.number_button);
        addButton = findViewById(R.id.add_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.i(TAG, "add button clicked");
                 RecycleManager recycleManager = RecycleManager.getInstance();
                 Plastic plastic = new Plastic(Double.parseDouble(numberButton.getNumber()), "kg");
                 Log.i(TAG, recycleManager.getRecycledItems().toString());
                 recycleManager.addToList(plastic);
                 Log.i(TAG, recycleManager.getRecycledItems().toString());

                 Intent intent = new Intent(DisplayRequirements.this, Sidebar.class);
                 String str = null;
                 intent.putExtra(INTENT_KEY, INTENT_VALUE);
                 startActivity(intent);
             }
         });


//        btnCart = findViewById(R.id.btnCart);
//
//        category_description = findViewById(R.id.category_description);
//        category_name = findViewById(R.id.category_name);
//        category_price = findViewById(R.id.category_money);
//        category_image = findViewById(R.id.category_image);
//
//        collapsingToolbarLayout = findViewById(R.id.collapsing);
//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
//        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
//
//        getDetailFood(position);

    }

//    private void getDetailFood(int pos) {
//        switch(pos){
//
//            case 0:
//                Plastic plastic = new Plastic(0, null);
//                ImageView p = findViewById(R.id.layout_image);
//                p.setImageDrawable(getResources().getDrawable(R.drawable.large_plastic));
//                collapsingToolbarLayout.setTitle(plastic.getName());
//
//                Log.d(TAG, String.valueOf(category_price));
//
//                category_price.setText(Integer.toString(plastic.getPrice()));
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
///*
//            case 1:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
//            case 2:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
//            case 3:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
//            case 4:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
//            case 5:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
//            case 6:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
//            case 7:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
//            case 8:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//
//            case 9:
//                Plastic plastic = new Plastic();
//                category_image.setImageDrawable();
//                collapsingToolbarLayout.setTitle(plastic.getName());
//                category_price.setText(plastic.getPrice());
//                category_description.setText(plastic.getRecyclableRequirements());
//                break;
//*/
//        }
//    }
}
