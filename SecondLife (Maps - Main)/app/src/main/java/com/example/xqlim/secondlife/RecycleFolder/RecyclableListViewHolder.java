package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Glass;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;

import static android.content.ContentValues.TAG;

/**
 * RecyclableList View Holder that implements List View for recycleList
 */

public class RecyclableListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView categoryName;
    private ImageView categoryPhoto;
    private static final String TAG = "RecyclableListVHTAG";

    public RecyclableListViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryName = itemView.findViewById(R.id.category_image_name);
        categoryPhoto = itemView.findViewById(R.id.category_image);
    }

    /**
     * Sets functionality when item on the list is selected
     * @param view current View
     */
    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        int position = getAdapterPosition();
        Intent intent = new Intent(context, DisplayRequirements.class);
        intent.putExtra("position clicked", position);
        context.startActivity(intent);
    }

    public TextView getCategoryName() {
        return categoryName;
    }

    public ImageView getCategoryPhoto() {
        return categoryPhoto;
    }

    public void setCategoryName(String name) {
        this.categoryName.setText(name);
    }

    public void setCategoryPhoto(int image) {
        this.categoryPhoto.setImageResource(image);
    }
}

