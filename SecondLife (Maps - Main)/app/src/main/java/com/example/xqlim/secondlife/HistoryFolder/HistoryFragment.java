package com.example.xqlim.secondlife.HistoryFolder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;

public class HistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_history,container,false);
    }

    public void onResume(){
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("History");

    }

}

