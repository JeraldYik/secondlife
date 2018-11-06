package com.example.xqlim.secondlife.HistoryFolder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

public class HistoryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String[] myDataset = {"hi", "lel", "bob"};

        super.onCreateView(inflater, container, savedInstanceState);
        getActivity().setContentView(R.layout.fragment_history);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.history_recycler);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new HistAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        return inflater.inflate(R.layout.fragment_history,container,false);
    }

    public void onResume(){
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("History");

    }

}

