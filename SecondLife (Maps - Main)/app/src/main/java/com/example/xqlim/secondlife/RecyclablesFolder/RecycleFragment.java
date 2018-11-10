package com.example.xqlim.secondlife.RecyclablesFolder;

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
import android.widget.Button;
import android.widget.TextView;

import com.example.xqlim.secondlife.HistoryFolder.HistAdapter;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecycleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecycleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Recyclable> recycledItems;

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "Recycle";

    public RecycleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static HistoryFragment newInstance(String param1, String param2) {
//        HistoryFragment fragment = new HistoryFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        recycledItems.add(new Paper(3.7, "kg"));
        recycledItems.add(new Glass(2.4, "kg"));
        recycledItems.add(new MetalTin(5, "tins"));
        recycledItems.add(new SmallElectricalAppliance(4.6, "kg"));
        recycledItems.add(new AluminiumDrinkCan(12, "cans"));

    }

    public void addHist(Recyclable recyclable) {
        recycledItems.add(recyclable);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_recycle, container, false);
        Log.d(TAG,"test");
        //init recycled list
        recycledItems = new ArrayList<>();
        initializeData();

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.recyclable_recycler);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new HistAdapter(recycledItems);
        mRecyclerView.setAdapter(mAdapter);

        return layout;

    }


    @Override
    public void onResume() {
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("Recycle");

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*    Button one = (Button) findViewById(R.id.button);
    one.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            TextView output = (TextView) findViewById(R.id.output);
            output.append("1");
        }
    });*/

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
