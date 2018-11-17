package com.example.xqlim.secondlife.MapsFolder;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.xqlim.secondlife.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Control class to manage locations
 */

public class LocationManager
{
    private Context mContext;
    private static final String TAG = "LocationCreator";
    private HashMap <LatLng, Location> locationlist = new HashMap<>();


    private static LocationManager instance = new LocationManager();

    /**
     * Private Constructor for Singleton design principle
     */
    private LocationManager(){}

    public static LocationManager getInstance(){
        return instance;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

//
//    public LocationManager(Context context) {
//        this.mContext = context;
//    }

    /**
     * Parses KMLLayer to extract Location details
     * @param resource File to be parsed
     * @param category Category of Location
     * @throws XmlPullParserException
     * @throws IOException
     */
    public void readFile(int resource, String category) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        ArrayList<String> info = new ArrayList<>();
        String latlng_info;
        String text = "nth";
        String name = "no name";
        int counter = 0;

        InputStream object = mContext.getResources().openRawResource(resource);
        xpp.setInput(object, null);

        String tagName = xpp.getName();
        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            tagName = xpp.getName();

            if (eventType == XmlPullParser.START_DOCUMENT) {
            } else if (eventType == XmlPullParser.START_TAG) {
                if (tagName.equalsIgnoreCase("schemadata")) {
                }

            } else if (eventType == XmlPullParser.TEXT) {
                text = xpp.getText();
            } else if (eventType == XmlPullParser.END_TAG) {
                if (tagName.equalsIgnoreCase("simpledata")) {
                    info.add(counter, text);
                    counter++;
                } else if (tagName.equalsIgnoreCase("coordinates")){
                    info.add(counter, text);
                    createLocation(info, category);
                    counter = 0;
                    info.clear();

                } else if (tagName.equalsIgnoreCase("SchemaData")) {

                }
            }
            eventType = xpp.next();
        }
    }

    /**
     * Creates a new location object
     * @param info Location information
     * @param category Category of Location
     */

    private void createLocation(ArrayList<String> info, String category){

        Location location = new Location();
        location.setName(category);
        location.setDescription(info.get(0));
        if(category == "Cash For Trash") {
            switch(info.size()) {
                case 8:
                    for(String s : info) {
                        Log.i(TAG, s);
                    }
                    Log.i(TAG, "----------");
                    location.setAddressStreetName(info.get(1));
                    location.setAddressPostalCode(info.get(2));
                    location.setAddressBlockNumber(info.get(3));
                    location.setLatLng(makeLatLng(info.get(7)));
                    break;
                case 9:
                    location.setAddressStreetName(info.get(1));
                    location.setAddressPostalCode(info.get(2));
                    location.setAddressBuildingName(info.get(3));
                    location.setAddressBlockNumber(info.get(4));
                    location.setLatLng(makeLatLng(info.get(8)));
                    break;
                case 10:
                    location.setAddressUnitNumber(info.get(1));
                    location.setAddressStreetName(info.get(2));
                    location.setAddressPostalCode(info.get(3));
                    location.setAddressBuildingName(info.get(4));
                    location.setAddressBlockNumber(info.get(5));
                    location.setLatLng(makeLatLng(info.get(9)));
                    break;
            }
        }
        else if(category == "E-Waste") {
            switch(info.size()) {
                case 8:
                    location.setAddressPostalCode(info.get(1));
                    location.setAddressBlockNumber(info.get(2));
                    location.setAddressBuildingName(info.get(3));
                    location.setLatLng(makeLatLng(info.get(7)));
                    break;
                case 9:
                    location.setAddressPostalCode(info.get(1));
                    if(info.get(2).charAt(0)=='#') {
                        location.setAddressUnitNumber(info.get(2));
                    }
                    location.setAddressBlockNumber(info.get(3));
                    location.setAddressBuildingName(info.get(4));
                    location.setLatLng(makeLatLng(info.get(8)));
                    break;
                case 10:
                    location.setAddressPostalCode(info.get(1));
                    location.setAddressBlockNumber(info.get(4));
                    location.setLatLng(makeLatLng(info.get(9)));
                    if(info.get(3).charAt(0)=='#') {
                        location.setAddressUnitNumber(info.get(3));
                        location.setAddressBuildingName(info.get(5));
                    } else {
                        location.setAddressBuildingName(info.get(3));
                        location.setAddressUnitNumber(info.get(5));
                    }
                    break;
                case 11:
                    location.setAddressPostalCode(info.get(1));
                    location.setAddressUnitNumber(info.get(3));
                    location.setAddressBuildingName(info.get(4));
                    location.setAddressBlockNumber(info.get(5));
                    location.setAddressStreetName(info.get(6));
                    location.setLatLng(makeLatLng(info.get(10)));
                    break;
            }
        }


        locationlist.put(location.getLatLng(), location);
    }



    public HashMap<LatLng, Location> getLocationlist() {
        return locationlist;
    }

    public void setLocationlist(HashMap<LatLng, Location> locationlist) {
        this.locationlist = locationlist;
    }

    /**
     * Creates LatLng object from String Resource
     * @param rawLatLng String to be processed
     * @return LatLng
     */
    private LatLng makeLatLng(String rawLatLng) {
        String[] strLatLngArr = rawLatLng.split(",");
        LatLng latLng = new LatLng(Double.parseDouble(strLatLngArr[1]), Double.parseDouble(strLatLngArr[0]));
        return latLng;
    }
}