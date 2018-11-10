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

public class MarkerManager {

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

        switch(location.getName()){
            case "Cash for Trash":{
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(location.getLatLng())
                        .title(location.getName())
                        .snippet(snippetText)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                marker.setTag(location);
                break;
            }
            case "E-Waste":
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(location.getLatLng())
                        .title(location.getName())
                        .snippet(snippetText)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                marker.setTag(location);
                break;
        }
    }
}
