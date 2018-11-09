package com.example.xqlim.secondlife.HistoryFolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xqlim.secondlife.R;

import java.util.List;

public class HistAdapter extends RecyclerView.Adapter<HistAdapter.MyViewHolder> {
    private String[] mDataset;
    private List<Person> persons;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HistAdapter(List<Person> persons) {
        this.persons = persons;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclable_card, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.personName.setText(persons.get(i).name);
        holder.personAge.setText(persons.get(i).age);
        holder.personPhoto.setImageResource(persons.get(i).photoId);

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
        return persons.size();
    }
}
