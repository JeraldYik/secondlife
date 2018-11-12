package com.example.xqlim.secondlife.RecycleFolder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xqlim.secondlife.MapsFolder.MapViewFragment;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private String[] mDataset;
    private List<Recyclable> recyclables;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView recyclableName;
        TextView recyclableQty;
        ImageView recyclablePhoto;
        TextView recyclableButton;
        private View.OnClickListener mOnClickListener;
        private static final String TAG = "RecycleAdapter";




        public MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            recyclableName = itemView.findViewById(R.id.recyclable_name);
            recyclableQty = itemView.findViewById(R.id.recyclable_details);
            recyclablePhoto = itemView.findViewById(R.id.recyclable_photo);
            recyclableButton = itemView.findViewById(R.id.recycle_button);

            recyclableButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "clicked recycle button!!");

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MapViewFragment()).commit();
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
}
