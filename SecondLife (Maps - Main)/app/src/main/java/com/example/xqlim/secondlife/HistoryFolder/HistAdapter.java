package com.example.xqlim.secondlife.HistoryFolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.RecycleFolder.RecycleManager;

import java.util.List;


/**
 * Adapter for the ListView in FavouritesFragment
 */

public class HistAdapter extends RecyclerView.Adapter<HistAdapter.MyViewHolder> {

    /**
     * List of history items
     */
    private List<Recyclable> recyclables;

    /**
     * HistoryManager to manage history items
     */
    private HistoryManager historyManager;

    /**
     *Class to provide a reference to the views for each data item
     *Complex data items may need more than one view per item, and
     *you provide access to all the views for a data item in a view holder
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        /**
         * CardView to display each favourite
         */
        CardView cv;

        /**
         * Name of each History Item
         */
        TextView recyclableName;

        /**
         * Quantity of each History Item
         */
        TextView recyclableQty;

        /**
         * Photo to display for each History Item
         */
        ImageView recyclablePhoto;


        /**
         * Constructor for the ViewHolder
         * @param itemView View of each item to display
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            recyclableName = (TextView)itemView.findViewById(R.id.recyclable_name);
            recyclableQty = (TextView)itemView.findViewById(R.id.recyclable_details);
            recyclablePhoto = (ImageView)itemView.findViewById(R.id.recyclable_photo);

        }
    }

    /**
     * Constructor for the Adapter
     * @param recyclables Arraylist of the History Items
     */

    public HistAdapter(List<Recyclable> recyclables) {
        this.recyclables = recyclables;
        historyManager = HistoryManager.getInstance();
    }

    /**
     * Creates new view for each favourite (invoked by the layout manager)
     * @param parent View to display the created views in
     * @param viewType Type of view to create
     * @return ViewHolder to be created
     */
    @Override
    public HistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_card, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param holder ViewHolder to bind the view to
     * @param i Index of each element in the ArrayList of favourites
     */
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

    /**
     * Attaches the Adapter to the RecyclerView
     * @param recyclerView RecyclerView to attach the adapter to
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    /**
     * @return size of recyclables List
     */
    @Override
    public int getItemCount() {
        return recyclables.size();
    }


}


