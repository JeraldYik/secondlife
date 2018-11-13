package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Glass;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;

public class RecyclableListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView categoryName;
    private ImageView categoryPhoto;

    public RecyclableListViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryName = itemView.findViewById(R.id.category_name);
        categoryPhoto = itemView.findViewById(R.id.category_image);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "You chose to add: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
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

