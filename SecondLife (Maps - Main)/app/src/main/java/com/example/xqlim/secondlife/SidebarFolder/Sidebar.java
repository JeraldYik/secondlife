package com.example.xqlim.secondlife.SidebarFolder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.xqlim.secondlife.ChatbotFolder.ChatManager;
import com.example.xqlim.secondlife.ChatbotFolder.ChatManager;
import com.example.xqlim.secondlife.FavouritesFolder.FavouritesFragment;
import com.example.xqlim.secondlife.HistoryFolder.HistoryFragment;
import com.example.xqlim.secondlife.MapsFolder.MapViewFragment;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.RecycleFolder.DisplayRequirements;
import com.example.xqlim.secondlife.RecycleFolder.RecyclableList;
import com.example.xqlim.secondlife.RecycleFolder.RecycleFragment;

/**
 * Main activity of the app that allows users to select which fragment they want to open
 */

public class Sidebar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    Fragment mContent;
    private static final String TAG = "SidebarTAG";

    /**
     * Sets functionality of sidebar when app starts
     * @param savedInstanceState previous state of sidebar
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            //Restore the fragment's instance
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "MapViewFragment");

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int newString = 0;
        Bundle b = getIntent().getExtras();
        if(b!=null) {
            try{
                newString = (int) b.get(DisplayRequirements.INTENT_KEY);
            }catch(NullPointerException e){
                newString = 1;
                e.printStackTrace();
            }

        }
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(newString==100) {
            goToFragment(R.id.nav_recycle);
            navigationView.setCheckedItem(R.id.nav_mapview);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MapViewFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_mapview);
        }
    }



    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * Sets functionality of when an item on the sidebar is selected
     * @param item
     * @return true if item is selected successfully
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        goToFragment(item.getItemId());
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /**
     * Goes to the fragment selected
     * @param ID ID of fragment to open
     */
    private void goToFragment(int ID) {
        switch (ID){
            case R.id.nav_mapview:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapViewFragment()).commit();
                break;
            case R.id.nav_recycle:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RecycleFragment()).commit();
                break;
            case R.id.nav_favourites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavouritesFragment()).commit();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HistoryFragment()).commit();
                break;
            case R.id.nav_chatbot:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatManager()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance

        //getSupportFragmentManager().putFragment(outState, "MapViewFragment", mContent);
    }
}
