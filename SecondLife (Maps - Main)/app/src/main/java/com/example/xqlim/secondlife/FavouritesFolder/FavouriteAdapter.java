package com.example.xqlim.secondlife.FavouritesFolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xqlim.secondlife.MapsFolder.Location;
import com.example.xqlim.secondlife.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Adapter for the ListView in FavouritesFragment
 */


public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {
    /**
     * Tag for Logging purposes
     */
    private static final String TAG = "FavouriteAdapterTAG";

    /**
     * List of favourites
     */
    private List<Location> Favourites;

    /**
     *Class to provide a reference to the views for each data item
     *Complex data items may need more than one view per item, and
     *you provide access to all the views for a data item in a view holder
     */


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /**
         * CardView to display each favourite
         */
        CardView cv;

        /**
         * Name of each favourite
         */
        TextView favouriteName;

        /**
         * Quantity of each favourite
         */
        TextView favouriteDetails;

        /**
         * Photo to display for each favourite
         */
        ImageView favouritePhoto;

        /**
         * Constructor for the ViewHolder
         * @param itemView View of each item to display
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv_favourite);
            favouriteName = (TextView) itemView.findViewById(R.id.favourites_name);
            favouriteDetails = (TextView) itemView.findViewById(R.id.favourites_details);
            favouritePhoto = (ImageView) itemView.findViewById(R.id.favourites_photo);
        }
    }

    /**
     * Constructor for the Adapter
     * @param favourites Arraylist of the Locations that are favourited
     */

    public FavouriteAdapter(ArrayList<Location> favourites) {
        this.Favourites = favourites;
    }

    /**
     * Creates new view for each favourite (invoked by the layout manager)
     * @param parent View to display the created views in
     * @param viewType Type of view to create
     * @return Viewholder to be created
     */

    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_card, parent, false);

        FavouriteAdapter.MyViewHolder vh = new FavouriteAdapter.MyViewHolder(v);
        return vh;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param holder ViewHolder to bind the view to
     * @param i Index of each element in the ArrayList of favourites
     */

    @Override
    public void onBindViewHolder(FavouriteAdapter.MyViewHolder holder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Location favouritedLocation = Favourites.get(i);
        holder.favouriteName.setText(favouritedLocation.getName());
        String favouriteText = (favouritedLocation.getAddressBuildingName()==null) ?
                (favouritedLocation.getAddressBlockNumber() + " " + favouritedLocation.getAddressStreetName()) :
                favouritedLocation.getAddressBuildingName();
        holder.favouriteDetails.setText(favouriteText);
        switch(favouritedLocation.getName()){
            case "Cash For Trash":
                holder.favouritePhoto.setImageResource(R.drawable.cash_for_trash);
                break;
            case "E-Waste":
                holder.favouritePhoto.setImageResource(R.drawable.small_appliance);
                break;
        }

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
     * @return size of favourites ArrayList
     */

    @Override
    public int getItemCount() {
        return Favourites.size();
    }


}
