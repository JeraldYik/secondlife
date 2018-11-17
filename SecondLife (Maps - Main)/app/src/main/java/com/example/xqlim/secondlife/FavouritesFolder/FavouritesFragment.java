package com.example.xqlim.secondlife.FavouritesFolder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xqlim.secondlife.HistoryFolder.HistoryFragment;
import com.example.xqlim.secondlife.MapsFolder.Location;
import com.example.xqlim.secondlife.MapsFolder.MapViewFragment;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

import java.util.ArrayList;

/**
 * Boundary Class for Favourites that display Favourites in a ListView
 */

public class FavouritesFragment extends Fragment {

    /**
     * RecyclerView container for the ListView
     */
    private RecyclerView mRecyclerView;
    /**
     * Adapter for the RecyclerView
     */
    private RecyclerView.Adapter mAdapter;
    /**
     * Layout Manager for the RecyclerView
     */
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * ArrayList of Favourite Locations
     */
    private ArrayList<Location> Favourites;

    /**
     * Listener for any interactions with the Fragment
     */
    private FavouritesFragment.OnFragmentInteractionListener mListener;

    /**
     * TAG for logging purposes
     */

    private static final String TAG = "Favourite";

    /**
     * Constructor for the fragment
     */

    public FavouritesFragment() {
        // Required empty public constructor
    }

    /**
     * gets list of Favourites from Favourite Manager
     */

    private void initializeData() {
        Favourites = MapViewFragment.getFavouritesManager().getFavouriteList();
    }

    /**
     * Creates the Fragment based on the savedInstanceState
     * @param savedInstanceState Saved state of the fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the View to be displayed
     * @param inflater Layout inflater that inflates the layout
     * @param container The parent view that the fragment's UI should be attached to
     * @param savedInstanceState Saved state of the fragment
     * @return View to be created
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_favourites, container, false);
        Log.d(TAG,"test");
        //init favourite list

        initializeData();

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.favourites_recycler);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new FavouriteAdapter(Favourites);
        mRecyclerView.setAdapter(mAdapter);

        return layout;

    }

    /**
     * Resumes fragment activity.
     */
    @Override
    public void onResume() {
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("Favourites");

    }

    /**
     * Listener for on when back button is pressed
     * @param uri Resource Identifier
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * Sets functionality when fragment is attached
     * @param context Context of the fragment
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * Sets functionality when fragment is detached
     */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

