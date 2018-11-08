package com.example.xqlim.secondlife.FavouritesFolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xqlim.secondlife.MapsFolder.Location;
import com.example.xqlim.secondlife.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {
    private String[] mDataset;
    private List<Location> Favourites;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView favouriteName;
        TextView favouriteQty;
        ImageView favouritePhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv_favourite);
            favouriteName = (TextView) itemView.findViewById(R.id.favourites_name);
            favouriteQty = (TextView) itemView.findViewById(R.id.favourites_details);
            favouritePhoto = (ImageView) itemView.findViewById(R.id.favourites_photo);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FavouriteAdapter(ArrayList<Location> favourites) {
        this.Favourites = favourites;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_card, parent, false);

        FavouriteAdapter.MyViewHolder vh = new FavouriteAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(FavouriteAdapter.MyViewHolder holder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.favouriteName.setText(Favourites.get(i).getName());
        holder.favouriteQty.setText(Favourites.get(i).getSnippetText());
        holder.favouritePhoto.setImageResource(R.drawable.sammyyeh);

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
        return Favourites.size();
    }

    {
    }
}
