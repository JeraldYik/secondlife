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
import com.example.xqlim.secondlife.RecyclablesFolder.MetalTin;
import com.example.xqlim.secondlife.RecyclablesFolder.Paper;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        recycledItems.add(new Paper(5, "kg"));
        recycledItems.add(new MetalTin(5, "cans"));
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
        View layout = inflater.inflate(R.layout.fragment_hist, container, false);

        //init recycled list
        recycledItems = new ArrayList<>();
        initializeData();

        writeToMem(recycledItems);
        ArrayList<Recyclable> bob = readFromMem(recycledItems);

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.history_recycler);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new HistAdapter(bob);
        mRecyclerView.setAdapter(mAdapter);

        return layout;

    }

    public void writeToMem(ArrayList arraylist) {
        //open datafile
        try {
            FileOutputStream fos = getContext().openFileOutput("histList", Context.MODE_PRIVATE);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(arraylist);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList readFromMem(ArrayList arraylist) {
        try
        {
            FileInputStream fis = getContext().openFileInput("hist_list");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return null;
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        return arraylist;
    }

    @Override
    public void onResume() {
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
