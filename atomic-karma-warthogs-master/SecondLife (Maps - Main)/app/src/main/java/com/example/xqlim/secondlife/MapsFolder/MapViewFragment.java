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
import android.view.MenuItem;
import android.view.MotionEvent;
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

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * An activity that displays a map showing the place at the device's current location.
 */
public class MapViewFragment extends Fragment
        implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = "MapViewLog";
    private MapView mapView;
    private GoogleMap mMap;
    private CameraPosition mCameraPosition;
    private DrawerLayout drawer;
    private FavouritesManager favouritesManager;

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

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] mLikelyPlaceNames;
    private String[] mLikelyPlaceAddresses;
    private String[] mLikelyPlaceAttributions;
    private LatLng[] mLikelyPlaceLatLngs;

    //initialise fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "initialising mapviewfrag");
        return inflater.inflate(R.layout.fragment_map_view, container, false);
    }

    public void onResume(){
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("Map");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * Sets up the options menu.
     * @param menu The options menu.
     * @return Boolean.
     */

    //menu for getting suggested nearby locations
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.current_place_menu, menu);
//        return true;
//    }

    /**
     * Handles a click on the menu option to get a place.
     * @param item The menu item to handle.
     * @return Boolean.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.option_get_place) {
            showCurrentPlace();
        }
        return true;
    }

    /**
     * Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getContext()));
        mMap.setOnInfoWindowClickListener(this);

        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        addLayers();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        com.example.xqlim.secondlife.MapsFolder.Location retrieved_location = (com.example.xqlim.secondlife.MapsFolder.Location) marker.getTag(); //unable to typecast
        if(retrieved_location.favourited()) {
            marker.setIcon(BitmapDescriptorFromVector(getContext(), R.drawable.orange_stars));
            favouritesManager.addFavourite(retrieved_location);
        }
        else {
            switch(retrieved_location.getName().toLowerCase()){
                case "cash for trash":
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            }
            favouritesManager.deleteFavourite(retrieved_location);
        }
        Log.i(TAG, favouritesManager.toString());
    }

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
//
//        /*
//         * Get the best and most recent location of the device, which may be null in rare
//         * cases when a location is not available.
//         */
//        try {
//            if (mLocationPermissionGranted) {
//                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
//                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "getting loc");
//                            // Set the map's camera position to the current location of the device.
//                            mLastKnownLocation = task.getResult();
//                            Log.d(TAG, "Lat: " + mLastKnownLocation.getLatitude());
//                            Log.d(TAG, "Long: " + mLastKnownLocation.getLongitude());
//                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                    new LatLng(mLastKnownLocation.getLatitude(),
//                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
//                        } else {
//                            Log.d(TAG, "Current location is null. Using defaults.");
//                            Log.e(TAG, "Exception: %s", task.getException());
//                            mMap.moveCamera(CameraUpdateFactory
//                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
//                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//                        }
//                    }
//                });
//            }
//        } catch (SecurityException e)  {
//            Log.e("Exception: %s", e.getMessage());
//        }
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
     * Prompts the user to select the current place from a list of likely places, and shows the
     * current place on the map - provided the user has granted location permission.
     */
    private void showCurrentPlace() {
        if (mMap == null) {
            return;
        }

        if (mLocationPermissionGranted) {
            // Get the likely places - that is, the businesses and other points of interest that
            // are the best match for the device's current location.
            @SuppressWarnings("MissingPermission") final
            Task<PlaceLikelihoodBufferResponse> placeResult =
                    mPlaceDetectionClient.getCurrentPlace(null);
            placeResult.addOnCompleteListener
                    (new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();

                                // Set the count, handling cases where less than 5 entries are returned.
                                int count;
                                if (likelyPlaces.getCount() < M_MAX_ENTRIES) {
                                    count = likelyPlaces.getCount();
                                } else {
                                    count = M_MAX_ENTRIES;
                                }

                                int i = 0;
                                mLikelyPlaceNames = new String[count];
                                mLikelyPlaceAddresses = new String[count];
                                mLikelyPlaceAttributions = new String[count];
                                mLikelyPlaceLatLngs = new LatLng[count];

                                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                                    // Build a list of likely places to show the user.
                                    mLikelyPlaceNames[i] = (String) placeLikelihood.getPlace().getName();
                                    mLikelyPlaceAddresses[i] = (String) placeLikelihood.getPlace()
                                            .getAddress();
                                    mLikelyPlaceAttributions[i] = (String) placeLikelihood.getPlace()
                                            .getAttributions();
                                    mLikelyPlaceLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                                    i++;
                                    if (i > (count - 1)) {
                                        break;
                                    }
                                }

                                // Release the place likelihood buffer, to avoid memory leaks.
                                likelyPlaces.release();

                                // Show a dialog offering the user the list of likely places, and add a
                                // marker at the selected place.
//                                openPlacesDialog();

                            } else {
                                Log.e(TAG, "Exception: %s", task.getException());
                            }
                        }
                    });
        } else {
            // The user has not granted permission.
            Log.i(TAG, "The user did not grant location permission.");

            // Add a default marker, because the user hasn't selected a place.
            mMap.addMarker(new MarkerOptions()
                    .title(getString(R.string.default_info_title))
                    .position(mDefaultLocation)
                    .snippet(getString(R.string.default_info_snippet)));

            // Prompt the user for permission.
            getLocationPermission();
        }
    }

    /**
     * Displays a form allowing the user to select a place from a list of likely places.

    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = mLikelyPlaceLatLngs[which];
                String markerSnippet = mLikelyPlaceAddresses[which];
                if (mLikelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + mLikelyPlaceAttributions[which];
                }

                // Add a marker for the selected place, with an info window
                // showing information about that place.
                mMap.addMarker(new MarkerOptions()
                        .title(mLikelyPlaceNames[which])
                        .position(markerLatLng)
                        .snippet(markerSnippet));

                // Position the map's camera at the location of the marker.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,
                        DEFAULT_ZOOM));
            }
        };

        // Display the dialog.
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.pick_place)
                .setItems(mLikelyPlaceNames, listener)
                .show();
    }
     */
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

    private void addLayers() {
        Log.d(TAG, "add layers running");
        int counter = 0;
        try {
            Log.d(TAG, "try run layers");
            KmlLayer c4tLayer = new KmlLayer(mMap, R.raw.cashfortrash_kml, getContext());
            KmlLayer eWasteLayer = new KmlLayer(mMap, R.raw.ewaste_recycling_kml, getContext());

            //create locations & initialise favourite manager
            favouritesManager = new FavouritesManager();
            LocationManager locationManager = new LocationManager(getContext());
            locationManager.readFile(R.raw.cashfortrash_kml);

            KmlContainer container = c4tLayer.getContainers().iterator().next();
            container = container.getContainers().iterator().next();

            //to get all property frm 1 placemark
            /*
            KmlPlacemark placemark = container.getPlacemarks().iterator().next();
            Iterable<Object> properties = placemark.getProperties();

            for (Object property : properties){
                Log.d(TAG, property + "\n");
            }

            */

            //to get 1 property frm all placemarks
            Iterable<KmlPlacemark> iter = container.getPlacemarks();
            for (KmlPlacemark placemark : iter) {

                if(placemark.getGeometry().getGeometryType().equals("Point")) {
                    KmlPoint point = (KmlPoint) placemark.getGeometry();
                    LatLng latLng = new LatLng(point.getGeometryObject().latitude, point.getGeometryObject().longitude);
                    locationManager.getLocationlist().get(placemark.getProperty("name")).setLatLng(latLng);

                    //Log.d(TAG, "location added: " + locationManager.getLocationlist().get(placemark.getProperty("name")).getName());
                    addMarkers(locationManager.getLocationlist().get(placemark.getProperty("name")));


                    //Log.d(TAG, placemark.getProperty("name"));
                    //Log.d(TAG, latLng.toString());
                }
            }

            //locationManager.printLoc(locationManager.getLocationlist().get("kml_1"));

            //android util code
            /*
            for (int i = 1; i < 10; i++){
                KmlContainer container = layer.getContainers().iterator().next();
                //Retrieve a nested container within the first container
                container = container.getContainers().iterator().next();
                //Retrieve the first placemark in the nested container
                KmlPlacemark placemark = container.getPlacemarks().iterator().next();
                //Retrieve a polygon object in a placemark
                Log.d(TAG, placemark.getProperty("name"));
                Log.d(TAG, Integer.toString(i));
            }
            */


        } catch (XmlPullParserException | IOException e)  {
            Log.e("Exception: %s", e.getMessage());
        }


        /*

        KmlContainer container = kmlLayer.getContainers().iterator().next();
        //Retrieve a nested container within the first container
        container = container.getContainers().iterator().next();
        //Retrieve the first placemark in the nested container
        KmlPlacemark placemark = container.getPlacemarks().iterator().next();
        //Retrieve a polygon object in a placemark
        KmlPolygon polygon = (KmlPolygon) placemark.getGeometry();
         */

    }

    private void addMarkers(com.example.xqlim.secondlife.MapsFolder.Location location){
        String snippetText = location.getDescription() + "\n" +
                location.getAddressBlockNumber() + " " + location.getAddressStreetName() + "\n";
        if(location.getAddressUnitNumber() != null && location.getAddressBuildingName() != null) {
            snippetText += (location.getAddressUnitNumber() + ", " + location.getAddressBuildingName() + "\n");
        } else if(location.getAddressUnitNumber() == null && location.getAddressBuildingName() != null) {
            snippetText += (location.getAddressBuildingName() + "\n");
        }
        snippetText += "Singapore " + location.getAddressPostalCode();
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(location.getLatLng())
                .title(location.getName())
                .snippet(snippetText)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        marker.setTag(location);
    }
}
