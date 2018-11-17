package com.example.xqlim.secondlife.MapsFolder;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xqlim.secondlife.FavouritesFolder.FavouritesManager;
import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.kml.KmlContainer;
import com.google.maps.android.kml.KmlLayer;
import com.google.maps.android.kml.KmlPlacemark;
import com.google.maps.android.kml.KmlPoint;

import org.apache.commons.lang3.ObjectUtils;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that displays a map showing the place at the device's current location.
 */
public class MapViewFragment extends Fragment
        implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = "MapViewLog";
    private MapView mapView;
    private GoogleMap mMap;
    private CameraPosition mCameraPosition;
    private DrawerLayout drawer;
    private static FavouritesManager favouritesManager;
    private static MarkerManager markerManager;
    private static LocationManager locationManager;

    // The entry points to the Places API.
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-1.353655, 103.688101);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";


    int value = 0;

    private boolean isResume;


    /**
     * Initialises Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "initialising mapviewfrag");
        isResume = false;

        try {
            Bundle bundle = getArguments();
            this.value = bundle.getInt("recyclable");
            Log.d(TAG, "value: " + Integer.toString(value));
        }catch(NullPointerException e){
            Log.d(TAG, e.getMessage());
        }

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_map_view, container, false);
    }


    /**
     * Creates Filter Menu
     */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.filter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.showAll:
                markerManager.toggleMarkers("showAll");
                Log.d(TAG, "Show all selected");
                return true;
            case R.id.cashForTrash:
                markerManager.toggleMarkers("Cash For Trash");
                Log.d(TAG, "c4t selected");
                return true;
            case R.id.eWaste:
                markerManager.toggleMarkers("E-Waste");
                Log.d(TAG, "ewaste selected");
                return true;
            case R.id.Favourites:
                markerManager.toggleMarkers("Favourites");
                Log.d(TAG, "favourites selected");
                return true;
            default:
                Log.d(TAG, "menu item not found");
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Determines if the app is being run for the first time
     */

    public void onResume(){
        super.onResume();
        Log.d(TAG, "resuming");

        if (markerManager == null){
            Log.d(TAG, "null marker mgr");
        }
        else{
            isResume = true;
            Log.d(TAG, "size: " + Integer.toString(markerManager.getMarkerList().size()));

        }
        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("Map");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onviewcreated");
        super.onViewCreated(view, savedInstanceState);
//          Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(getContext(), null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(getContext(), null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Saves the state of the map when the activity is paused.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "Saving instance state");
        if (mMap != null) {

            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            Log.d(TAG, "on activity created");
        }
    }


    /**
     * Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        Log.d(TAG, "on map ready");
        mMap = map;

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getContext()));
        mMap.setOnInfoWindowClickListener(this);

        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        addLayers();


//        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }


    /**
     * Sets location to be favourited when infoWindow is clicked
     * @param marker marker that is clicked
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        com.example.xqlim.secondlife.MapsFolder.Location retrieved_location = (com.example.xqlim.secondlife.MapsFolder.Location) marker.getTag();
        Log.d(TAG, String.valueOf(retrieved_location.isFavourite()));

        retrieved_location.favourited();

        if(retrieved_location.isFavourite()) {
            marker.setIcon(BitmapDescriptorFromVector(getContext(), R.drawable.like));
            favouritesManager.addFavourite(retrieved_location);
        }
        else {
            switch(retrieved_location.getName().toLowerCase()){
                case "cash for trash":
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    break;
                case "e-waste":
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                    break;
            }
            favouritesManager.deleteFavourite(retrieved_location);
        }
//        Log.i(TAG, favouritesManager.toString());
    }

    /**
     * Retrieves Bitmap from PNG
     * @param context Context of activity
     * @param vectorResId Resource to parse
     * @return Bitmap retrieved
     */
    private BitmapDescriptor BitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */

    private void getDeviceLocation() {
        Log.d(TAG, "get device loc");
        Marker marker = mMap.addMarker(
                new MarkerOptions().position(new LatLng(1.353655, 103.688101)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(

                new LatLng(1.353655, 103.688101), DEFAULT_ZOOM));
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true); //not working on emulator
            return;
        }

        marker.remove();

    }


    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }


    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        Log.d(TAG, "updateUI");
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    /**
     * Add Markers to the map
     */
    private void addLayers() {
        Log.d(TAG, "add layers running");
        int counter = 0;
        try {
            Log.d(TAG, "try run layers");
            KmlLayer c4tLayer = new KmlLayer(mMap, R.raw.cashfortrash_kml, getContext());
            KmlLayer eWasteLayer = new KmlLayer(mMap, R.raw.ewaste_recycling_kml, getContext());
            if (!isResume){
                Log.d(TAG, "first time");
//            create locations & initialise favourite manager & marker manager
                favouritesManager = new FavouritesManager();
                markerManager = new MarkerManager();
//                LocationManager locationManager = new LocationManager(getContext());
                locationManager = LocationManager.getInstance();
                locationManager.setmContext(getContext());
                //locationManager = new LocationManager((getContext()));
                locationManager.readFile(R.raw.cashfortrash_kml, "Cash For Trash");
                locationManager.readFile(R.raw.ewaste_recycling_kml, "E-Waste");
            }
            else {
                //if its not the first time the fragment is created, don't initialise new managers
                // and read the file again

                Log.d(TAG, "resume!!");
            }

            Log.d(TAG, Integer.toString(locationManager.getLocationlist().size()));


            markerManager.setupMarker(c4tLayer, locationManager, "Cash For Trash", mMap, value);
            markerManager.setupMarker(eWasteLayer, locationManager, "E-Waste", mMap, value);

            for (LatLng key : markerManager.getMarkerList().keySet()){
                com.example.xqlim.secondlife.MapsFolder.Location location =  (com.example.xqlim.secondlife.MapsFolder.Location) markerManager.getMarkerList().get(key).getTag();
                if (location.isFavourite()){
                    markerManager.getMarkerList().get(key).setIcon(BitmapDescriptorFromVector(getContext(), R.drawable.like));
                }
            }
        } catch (XmlPullParserException | IOException e) {
            Log.e("Exception: %s", e.getMessage());
        }

    }

    public static FavouritesManager getFavouritesManager() {
        return favouritesManager;
    }
}