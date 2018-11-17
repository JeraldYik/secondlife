package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xqlim.secondlife.HistoryFolder.HistoryManager;
import com.example.xqlim.secondlife.MapsFolder.MapViewFragment;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

import java.util.List;

/**
 * Recycle Adapter for Recycle Fragment
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private String[] mDataset;
    private List<Recyclable> recyclables;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView recyclableName;
        TextView recyclableQty;
        ImageView recyclablePhoto;
        ImageView recyclableButton;
        private ImageView removeButton;
        private HistoryManager historyManager;
        private View.OnClickListener mOnClickListener;
        private static final String TAG = "RecycleAdapter";

        public MyViewHolder(final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            recyclableName = itemView.findViewById(R.id.recyclable_name);
            recyclableQty = itemView.findViewById(R.id.recyclable_details);
            recyclablePhoto = itemView.findViewById(R.id.recyclable_photo);
            recyclableButton = itemView.findViewById(R.id.recycle_button);
            removeButton = itemView.findViewById(R.id.remove_button);
            historyManager = HistoryManager.getInstance();

            recyclableButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "clicked recycle button!!");

                    Recyclable recycled = recyclables.get(getAdapterPosition());

                    historyManager.addHist(recycled);

                    Bundle bundl = new Bundle();
                    bundl.putInt("recyclable", 1);
                    MapViewFragment frag = new MapViewFragment();
                    frag.setArguments(bundl);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            frag).commit();

                    deleteItem(getAdapterPosition());
                    notifyDataSetChanged();


//                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new MapViewFragment()).commit();
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "clicked remove button!!");

                    deleteItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(),
                            "Removed recyclables", Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(List<Recyclable> recyclables) {
        this.recyclables = recyclables;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_card, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

//    @Override
//    public void onClick(final View view) {
//
//    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.recyclableName.setText(recyclables.get(i).getName());
        holder.recyclableQty.setText(recyclables.get(i).getQtyDisplay());
        holder.recyclablePhoto.setImageResource(recyclables.get(i).getImageAssetSmall());

        /*
        holder.mView.setText(mDataset[position]);
        */

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return recyclables.size();
    }

    void deleteItem(int index) {
        recyclables.remove(index);
        notifyItemRemoved(index);
    }
}
