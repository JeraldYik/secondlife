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

public class MarkerManager {

    private static final String TAG = "MarkerManager";
    private HashMap<String, Marker> markerList;

    public MarkerManager() {
        this.markerList = new HashMap<>();
    }

    public void setupMarker (KmlLayer kmlLayer, LocationManager locationManager, String category, GoogleMap mMap){
        KmlContainer container = kmlLayer.getContainers().iterator().next();
        container = container.getContainers().iterator().next();

        Iterable<KmlPlacemark> iter = container.getPlacemarks();
        for (KmlPlacemark placemark : iter) {
            if(placemark.getGeometry().getGeometryType().equals("Point")) {
                KmlPoint point = (KmlPoint) placemark.getGeometry();
                //retrieve latlng from location object itself, instead of setting
                LatLng latLng = new LatLng(point.getGeometryObject().latitude, point.getGeometryObject().longitude);
//                locationManager.getLocationlist().get(latLng).setLatLng(latLng);
//                Log.i(TAG, category + " + " + locationManager.getLocationlist().get(latLng).getName());
                addMarkers(locationManager.getLocationlist().get(latLng), category, mMap);
            }
        }

    }

    private void addMarkers(com.example.xqlim.secondlife.MapsFolder.Location location, String category, GoogleMap mMap){
        String snippetText = "";
        switch(category) {
            case "Cash for Trash":
                snippetText = location.getDescription() + "\n" +
                        location.getAddressBlockNumber() + " " + location.getAddressStreetName() + "\n";
                if (location.getAddressUnitNumber() != null && location.getAddressBuildingName() != null) {
                    snippetText += (location.getAddressUnitNumber() + ", " + location.getAddressBuildingName() + "\n");
                } else if (location.getAddressUnitNumber() == null && location.getAddressBuildingName() != null) {
                    snippetText += (location.getAddressBuildingName() + "\n");
                }
                snippetText += "Singapore " + location.getAddressPostalCode();
                break;
        }
        location.setSnippetText(snippetText);

        Marker marker = null;

        switch(location.getName()){
            case "cashForTrash":{
                marker = mMap.addMarker(new MarkerOptions()
                        .position(location.getLatLng())
                        .title(location.getName())
                        .snippet(snippetText)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                marker.setTag(location);

                break;
            }
            case "eWaste":
                marker = mMap.addMarker(new MarkerOptions()
                        .position(location.getLatLng())
                        .title(location.getName())
                        .snippet(snippetText)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                marker.setTag(location);
                break;
            default:
                Log.d(TAG, "Category not found");
        }
        markerList.put(location.getName(), marker);
    }

    public void toggleMarkers(String filter){
        switch(filter){
            case "showAll":
                for (String key : markerList.keySet()){
                    markerList.get(key).setVisible(true);
                }
                break;
            case "cashForTrash":
            case "eWaste":
                for (String key: markerList.keySet()){
                    if (key == filter){
                        markerList.get(key).setVisible(true);
                    }
                    else{
                        markerList.get(key).setVisible(false);
                    }
                }
                break;
            case "Favourites":
                for (String key : markerList.keySet()){
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
    }
}
