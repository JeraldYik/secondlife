package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;

import java.util.List;

/**
 * ImageAdapter for the ListView in FavouritesFragment
 */

public class RecyclableImageAdapter extends RecyclerView.Adapter<RecyclableListViewHolder> {

    private static final String TAG = "RecyclableList";

    private List<Recyclable> itemList;
    private Context context;

    public RecyclableImageAdapter(Context context, List<Recyclable> itemList) {
        this.itemList = itemList;
        this.context = context;

//        for (Recyclable re : itemList){
//            Log.d(TAG, re.getName());
//        }
    }


    @Override
    public RecyclableListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, null);
        RecyclableListViewHolder rcv = new RecyclableListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclableListViewHolder holder, int position) {
        Log.d(TAG, Integer.toString(position));
        Recyclable re = itemList.get((position));
        Log.d(TAG, re.getName());

        holder.setCategoryName(re.getName());
        holder.setCategoryPhoto(re.getImageAssetSmall());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

}