package com.example.xqlim.secondlife.RecycleFolder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecyclablesFolder.Glass;
import com.example.xqlim.secondlife.RecyclablesFolder.Paper;
import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

import java.util.ArrayList;

/**
 * Boundary Class for Recyclables that display possible recyclables in a ListView
 */
public class RecycleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;

    private RecycleManager recycleManager;

    private ArrayList<Recyclable> recycledItems;

    private static final String TAG = "RecycleFragmentTAG";

    public RecycleFragment() {
        this.recycledItems = new ArrayList<>();
        recycleManager = RecycleManager.getInstance();
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
        setHasOptionsMenu(true);
        View layout;
        Log.i(TAG, String.valueOf(recycleManager.getRecycledItems().size() == 0));
        if(recycleManager.getRecycledItems().size() == 0) {
            layout = inflater.inflate(R.layout.no_element_in_list, container, false);
        } else {
            layout = inflater.inflate(R.layout.fragment_recycle, container, false);

            //        recycleManager.initializeData();
            for(Recyclable r : recycleManager.getRecycledItems()) {
                Log.i(TAG, r.getName() + " " + r.getQtyDisplay());
            }

            mRecyclerView = layout.findViewById(R.id.recyclable_recycler);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new RecycleAdapter(recycleManager.getRecycledItems());
            mRecyclerView.setAdapter(mAdapter);
        }


        return layout;

    }

    /**
     * Creates the option menu to allow for adding of recyclables
     * @param menu Menu to be inflated
     * @param inflater Inflater to be used
     */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.recycle_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    /**
     * Implements functionality when options item is selected
     * @param item item that is selected
     * @return Boolean that indicates if the item has been selected
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_recycle_item) {
            // do something here
            Log.d(TAG, "clicked button");
            Intent intent = new Intent(getActivity(), RecyclableList.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Resumes fragment activity.
     */

    @Override
    public void onResume() {
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("Recycle");

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
