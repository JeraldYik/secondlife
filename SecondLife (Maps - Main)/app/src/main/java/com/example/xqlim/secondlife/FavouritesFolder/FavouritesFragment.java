package com.example.xqlim.secondlife.FavouritesFolder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xqlim.secondlife.HistoryFolder.Hist;
import com.example.xqlim.secondlife.HistoryFolder.HistAdapter;
import com.example.xqlim.secondlife.MapsFolder.Location;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.MetalTin;
import com.example.xqlim.secondlife.RecyclablesFolder.Paper;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Location> Favourites;

    private String filename = "hist_list.ser";

    private Hist.OnFragmentInteractionListener mListener;

    private static final String TAG = "Favourite";

    public FavouritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hist.
     */
    // TODO: Rename and change types and number of parameters
//    public static Hist newInstance(String param1, String param2) {
//        Hist fragment = new Hist();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

// Checkout the project associated with this tutorial on Github if
// you want to use the same images.

    private void initializeData() {
        Favourites.add(new Location());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_favourites, container, false);
        Log.d(TAG,"test");
        //init favourite list
        Favourites = new ArrayList<>();

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


    @Override
    public void onResume() {
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("Favourites");

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

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

