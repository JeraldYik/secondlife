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

    private static final String TAG = "MarkerManagerTAG";
    //key is latlng, value is marker
    private HashMap<LatLng, Marker> markerList;

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
                LatLng latLng = new LatLng(point.getGeometryObject().latitude, point.getGeometryObject().longitude);
                addMarkers(locationManager.getLocationlist().get(latLng), category, mMap);
            }
        }

    }

    private void addMarkers(com.example.xqlim.secondlife.MapsFolder.Location location, String category, GoogleMap mMap){
        Marker marker;
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
                markerList.put(location.getLatLng(), marker);
                break;
        //& toner a bit of issue
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
                markerList.put(location.getLatLng(), marker);
                break;
        }
        location.setSnippetText(snippetText);

    }

    //Filter which markers to show
    public void toggleMarkers(String filter){

        switch(filter){
            case "showAll":
                Log.d(TAG, "Showall filter");
                for (LatLng key : markerList.keySet()){
                    markerList.get(key).setVisible(true);
                }
                break;
            case "Cash For Trash":
            case "E-Waste":
                Log.d(TAG, "Category filter: " + filter);

                for (LatLng key: markerList.keySet()){
                    Location location = (Location) markerList.get(key).getTag();

                    if (location.getName() == filter){
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
    }
}
