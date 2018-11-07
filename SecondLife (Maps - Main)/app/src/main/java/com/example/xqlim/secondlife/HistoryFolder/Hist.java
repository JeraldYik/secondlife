package com.example.xqlim.secondlife.HistoryFolder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.*;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Hist.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Hist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hist extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Person> persons;
    private ArrayList<Recyclable> recycledItems;

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "History";

    public Hist() {
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

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.emma));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.emma));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.emma));

        recycledItems = new ArrayList<>();
        recycledItems.add(new Paper(5,"kg"));
        recycledItems.add(new MetalTin(5, "cans"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_hist, container, false);

        //mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.history_recycler);

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.history_recycler);

        initializeData();

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new HistAdapter(recycledItems);
        mRecyclerView.setAdapter(mAdapter);

        return layout;

        //return inflater.inflate(R.layout.fragment_hist, container, false);


    }

    @Override
    public void onResume(){
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("History");

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
