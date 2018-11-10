package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;

import java.util.List;

public class RecyclableImageAdapter extends RecyclerView.Adapter<RecyclableListViewHolder> {

    private List<Recyclable> itemList;
    private Context context;

    public RecyclableImageAdapter(Context context, List<Recyclable> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclableListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclable_list, null);
        RecyclableListViewHolder rcv = new RecyclableListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclableListViewHolder holder, int position) {
        holder.categoryName.setText(itemList.get(position).getName());
        holder.categoryPhoto.setImageResource(itemList.get(position).getImageAssetSmall());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

}