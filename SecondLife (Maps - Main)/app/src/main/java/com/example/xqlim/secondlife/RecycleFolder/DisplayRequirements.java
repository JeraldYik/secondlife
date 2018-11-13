package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Plastic;

public class DisplayRequirements extends AppCompatActivity {

    TextView category_name, category_price, category_description;
    ImageView category_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    private int position;

    public void positionClicked() {
        Intent in = getIntent();
        Bundle b = in.getExtras();
        position = b.getInt("position clicked");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_requirements);

        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);

        category_description = findViewById(R.id.category_description);
        category_name = findViewById(R.id.category_name);
        category_price = findViewById(R.id.category_money);
        category_image = findViewById(R.id.category_image);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        getDetailFood(position);

    }

    private void getDetailFood(int pos) {
        switch(pos){

            case 0:
                Plastic plastic = new Plastic();
                ImageView p = findViewById(R.id.category_image);
                p.setImageDrawable(getResources().getDrawable(R.drawable.large_plastic));
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

/*
            case 1:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

            case 2:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

            case 3:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

            case 4:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

            case 5:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

            case 6:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

            case 7:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

            case 8:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;

            case 9:
                Plastic plastic = new Plastic();
                category_image.setImageDrawable();
                collapsingToolbarLayout.setTitle(plastic.getName());
                category_price.setText(plastic.getPrice());
                category_description.setText(plastic.getRecyclableRequirements());
                break;
*/
        }
    }
}
