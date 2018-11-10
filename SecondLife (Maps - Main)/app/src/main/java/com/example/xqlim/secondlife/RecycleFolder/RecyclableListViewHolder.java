package com.example.xqlim.secondlife.RecycleFolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xqlim.secondlife.R;

public class RecyclableListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView categoryName;
    public ImageView categoryPhoto;

    public RecyclableListViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryName = (TextView)itemView.findViewById(R.id.recyclable_name);
        categoryPhoto = (ImageView)itemView.findViewById(R.id.recyclable_photo);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }

}

