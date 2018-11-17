package com.example.xqlim.secondlife.MapsFolder;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.kml.KmlContainer;
import com.google.maps.android.kml.KmlLayer;
import com.google.maps.android.kml.KmlPlacemark;
import com.google.maps.android.kml.KmlPoint;

import java.util.HashMap;

/**
 * Control Class to manage markers
 */

public class MarkerManager {

    /**
     * TAG for logging purposes
     */
    private static final String TAG = "MarkerManagerTAG";

    /**
     * Hashmap of markers
     * Key is LatLng of location, value is marker to be displayed
     */
    private HashMap<LatLng, Marker> markerList;

    /**
     * filter that determines which markers are shown
     */
    private String filter;

    /**
     * Constructor for markerManager
     * Instantiates markerList
     * Sets filter to nothing
     */
    public MarkerManager() {
        this.markerList = new HashMap<>();
        filter = "nothing";
    }

    /**
     * Retrieves list of all coordinates from KMLLayer
     * @param kmlLayer KMLLayer to extract coordinates from
     * @param locationManager locationManager to retrieve list of locations from
     * @param category determines which category of location the marker is representing
     * @param mMap Map to display markers on
     * @param value Sets default filter for map
     */

    public void setupMarker (KmlLayer kmlLayer, LocationManager locationManager, String category, GoogleMap mMap, int value){
        KmlContainer container = kmlLayer.getContainers().iterator().next();
        container = container.getContainers().iterator().next();

        Iterable<KmlPlacemark> iter = container.getPlacemarks();
        for (KmlPlacemark placemark : iter) {
            if(placemark.getGeometry().getGeometryType().equals("Point")) {
                KmlPoint point = (KmlPoint) placemark.getGeometry();
                LatLng latLng = new LatLng(point.getGeometryObject().latitude, point.getGeometryObject().longitude);
                addMarkers(locationManager.getLocationlist().get(latLng), category, mMap);
            }
        }

        if (value == 1){
            toggleMarkers("Cash For Trash");
        }
        else{
            toggleMarkers(filter);
        }

    }

    /**
     * Creates markers and adds them to map
     * @param location Location whose info should be displayed
     * @param category Category of marker
     * @param mMap Map to add markers to
     */

    private void addMarkers(com.example.xqlim.secondlife.MapsFolder.Location location, String category, GoogleMap mMap){
        Marker marker = null;
        String snippetText = "";
        switch(category) {
            case "Cash For Trash":
                snippetText = location.getDescription() + "\n";
                snippetText += location.getAddressBlockNumber() + " " + location.getAddressStreetName() + "\n";
                if (location.getAddressUnitNumber() != null && location.getAddressBuildingName() != null) {
                    snippetText += (location.getAddressUnitNumber() + ", " + location.getAddressBuildingName() + "\n");
                } else if (location.getAddressUnitNumber() == null && location.getAddressBuildingName() != null) {
                    snippetText += (location.getAddressBuildingName() + "\n");
                }
                snippetText += "Singapore " + location.getAddressPostalCode();


                marker = mMap.addMarker(new MarkerOptions()
                        .position(location.getLatLng())
                        .title(location.getName())
                        .snippet(snippetText)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));


                marker.setTag(location);

                break;

            case "E-Waste":
                snippetText = location.getDescription();
                if(location.getAddressBlockNumber()!=null && location.getAddressStreetName()!=null) {
                    snippetText += location.getAddressBlockNumber() + " " + location.getAddressStreetName() + "\n";
                } else if(location.getAddressUnitNumber() != null) {
                    snippetText += location.getAddressUnitNumber() + ", ";
                }
                snippetText += location.getAddressBuildingName() + "\n";
                snippetText += "Singapore " + location.getAddressPostalCode();

                marker = mMap.addMarker(new MarkerOptions()
                        .position(location.getLatLng())
                        .title(location.getName())
                        .snippet(snippetText)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                marker.setTag(location);
                marker.setVisible(false);

                break;
            default:
                Log.d(TAG, "Default case");
        }

        markerList.put(location.getLatLng(), marker);
        location.setSnippetText(snippetText);

    }

    /**
     * Filters which markers to show
     * @param filter filter to be applied
     */

    public void toggleMarkers(String filter){
        Log.d(TAG, Integer.toString(markerList.size()));

        switch(filter){
            case "showAll":

                for (LatLng key : markerList.keySet()){
                    markerList.get(key).setVisible(true);
                }
                break;
            case "Cash For Trash":
            case "E-Waste":


                for (LatLng key: markerList.keySet()){
                    Location location = (Location) markerList.get(key).getTag();


                    if (location.getName() == filter){
                        Log.d(TAG, "Category filter: " + filter + ", location name: " + location.getName() + "set to true");
                        markerList.get(key).setVisible(true);
                    }
                    else{
                        markerList.get(key).setVisible(false);
                    }
                }
                break;
            case "Favourites":
                Log.d(TAG, "favourite filter");
                for (LatLng key : markerList.keySet()){
                    Marker marker = markerList.get(key);
                    Location location = (Location) marker.getTag();
                    if (location.isFavourite()){
                        markerList.get(key).setVisible(true);
                    }
                    else{
                        markerList.get(key).setVisible(false);
                    }
                }
                break;
        }
        this.filter = filter;
    }

    /**
     * Getter for MarkerList
     * @return MarkerList
     */
    public HashMap<LatLng, Marker> getMarkerList() {
        return markerList;
    }


}
