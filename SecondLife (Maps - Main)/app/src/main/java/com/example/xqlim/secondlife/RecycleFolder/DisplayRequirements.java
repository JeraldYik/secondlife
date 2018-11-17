package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.AluminiumDrinkCan;
import com.example.xqlim.secondlife.RecyclablesFolder.Plastic;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

/**
 * Class that shows requirements of recyclable to be recycled and allows user to input quantity
 */

public class DisplayRequirements extends AppCompatActivity {

    private static final String TAG = "DisplayRequirementsTag";
    private TextView category_name, category_location, category_description, category_unit;
    private ImageView category_image;
    private ElegantNumberButton numberButton;
    private Button addButton;
    public static final String INTENT_KEY = "recycle_intent_key";
    public static final int INTENT_VALUE = 100;
    private int position;

    /**
     * Sets functionality when activity is created
     * @param savedInstanceState State of activity when it was paused or destroyed
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_requirements_plastic);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        position = b.getInt("position clicked");

        category_image = findViewById(R.id.category_image);
        category_name = findViewById(R.id.category_name);
        category_location = findViewById(R.id.category_location);
        category_description = findViewById(R.id.category_description);
        category_unit = findViewById(R.id.category_unit);
        numberButton = findViewById(R.id.number_button);
        addButton = findViewById(R.id.add_btn);

        getRecyclingDetails(position);


        addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.i(TAG, "add button clicked");
                 RecycleManager recycleManager = RecycleManager.getInstance();

                 switch (position) {

                     case 0:

                         Plastic plastic = new Plastic(Double.parseDouble(numberButton.getNumber()), "kg");
                         recycleManager.addToList(plastic);

                         break;

                     case 3:

                         AluminiumDrinkCan aluminium = new AluminiumDrinkCan(Double.parseDouble(numberButton.getNumber())*0.02, "kg");
                         recycleManager.addToList(aluminium);

                         break;

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

                     default:
                         break;
                 }

                 Intent intent = new Intent(DisplayRequirements.this, Sidebar.class);
                 String str = null;
                 intent.putExtra(INTENT_KEY, INTENT_VALUE);
                 startActivity(intent);

             }
         });
        }

    /**
     * Sets the Details of each recyclable
     * @param pos position of recyclable in the List
     */

    private void getRecyclingDetails(int pos) {
        switch(pos){

            case 0:
                Plastic plastic = new Plastic(0, "kg");
                ImageView p = findViewById(R.id.category_image);
                p.setImageDrawable(getResources().getDrawable(R.drawable.large_plastic));
                category_name.setText(plastic.getName());
                category_location.setText(plastic.getLocation());
                category_description.setText(plastic.getRecyclableRequirements());
                category_unit.setText(plastic.getUnit());
                break;

            case 3:
                AluminiumDrinkCan cans = new AluminiumDrinkCan(0, "cans");
                ImageView a = findViewById(R.id.category_image);
                a.setImageDrawable(getResources().getDrawable(R.drawable.large_cans));
                category_name.setText(cans.getName());
                category_location.setText(cans.getLocation());
                category_description.setText(cans.getRecyclableRequirements());
                category_unit.setText(cans.getUnit());
                break;

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

            default:
                break;
        }
    }
}
