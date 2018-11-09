package com.example.xqlim.secondlife.MapsFolder;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.xqlim.secondlife.R;

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

public class LocationManager
{
    private Context mContext;
    private static final String TAG = "LocationCreator";
    private HashMap <String, Location> locationlist = new HashMap<>();
    private HashMap <String, HashMap<String, Location>> categoryList = new HashMap<>();

    public LocationManager(Context context) {
        this.mContext = context;
    }

    public void readFile(int resource) throws XmlPullParserException, IOException{
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        ArrayList<String> info = new ArrayList<>();
        String text = "nth";
        String name = "no name";
        int counter = 0;

        InputStream object = mContext.getResources().openRawResource(resource);
        xpp.setInput(object, null);

        String tagName = xpp.getName();
        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            tagName = xpp.getName();


            if(eventType == XmlPullParser.START_DOCUMENT) {
                //Log.d(TAG, "Start document");
            } else if(eventType == XmlPullParser.START_TAG) {
                if (tagName.equalsIgnoreCase("schemadata")){
                }
                //Log.d(TAG, "Start tag "+xpp.getName());

            }
            else if(eventType == XmlPullParser.TEXT) {
                text = xpp.getText();
                //Log.d(TAG,"Text "+xpp.getText());
            }
            else if(eventType == XmlPullParser.END_TAG) {
                //Log.d(TAG,"End tag "+xpp.getName());
                if (tagName.equalsIgnoreCase("name")){

                    name = text;
                    //Log.d(TAG, "name: " + name);
                }
                else if (tagName.equalsIgnoreCase("simpledata")){
                    info.add(counter, text);
                    //Log.d(TAG, "Simpledata: " + counter + ". " + text);
                    counter++;
                }
                else if (tagName.equalsIgnoreCase("SchemaData")){
                    /*
                    Log.d(TAG, "Schema Data");
                    for (int i = 0; i < info.size(); i++){
                        Log.d(TAG, "info: " + info.get(i));
                    }
                    */
                    createLocation(name, info);
                    counter = 0;
                    info.clear();
                }
            }
            eventType = xpp.next();
        }
        //Log.d(TAG,"End document");
    }


    private void createLocation(String name, ArrayList<String> info){
        //Log.d(TAG, "location creating");

        Location location = new Location();
        location.setName("Cash For Trash");
        //Log.d(TAG, location.getName());
        location.setAddressBlockNumber(info.get(info.size()-4));

        location.setAddressPostalCode(info.get(info.size()-6));
        location.setAddressStreetName(info.get(info.size()-7));
        //Log.d(TAG, location.getAddressStreetName());
        if (info.size() == 9){
            location.setAddressBlockNumber(info.get(info.size()-4));
            location.setAddressBuildingName(info.get(info.size()-5));
            location.setAddressPostalCode(info.get(info.size()-6));
            location.setAddressStreetName(info.get(info.size()-7));
            location.setAddressUnitNumber(info.get(1));
        }
        else if (info.size() == 8){
            location.setAddressBlockNumber(info.get(info.size()-4));
            location.setAddressBuildingName(info.get(info.size()-5));
            location.setAddressPostalCode(info.get(info.size()-6));
            location.setAddressStreetName(info.get(info.size()-7));
//            location.setAddressUnitNumber("No unit number");
        }

        else if (info.size() == 7){
            location.setAddressBlockNumber(info.get(info.size()-4));
            location.setAddressPostalCode(info.get(info.size()-5));
            location.setAddressStreetName(info.get(info.size()-6));
//            location.setAddressBuildingName("No Building Name");
//            location.setAddressUnitNumber("No unit number");
        }
        location.setDescription(info.get(0));
        locationlist.put(name, location);
        //printLoc(location);
    }

    //dont use unless NULL is addressed
    public void printLoc(Location location){
        Log.d(TAG, location.getName());
        Log.d(TAG, location.getDescription());
        Log.d(TAG, location.getAddressUnitNumber());
        Log.d(TAG, location.getAddressStreetName());
        Log.d(TAG, location.getAddressPostalCode());
        Log.d(TAG, location.getAddressBuildingName());
        Log.d(TAG, location.getAddressBlockNumber());
        Log.d(TAG, location.getLatLng().toString());
    }

    public HashMap<String, Location> getLocationlist() {
        return locationlist;
    }

    public void setLocationlist(HashMap<String, Location> locationlist) {
        this.locationlist = locationlist;
    }
}