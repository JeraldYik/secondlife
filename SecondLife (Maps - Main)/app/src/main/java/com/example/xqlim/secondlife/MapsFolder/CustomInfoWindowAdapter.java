package com.example.xqlim.secondlife.MapsFolder;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.xqlim.secondlife.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;



/**
 * Demonstrates customizing the info window and/or its contents
 * Info window is displayed when user selects a marker on the map
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

    // These are both viewgroups containing an ImageView with id "badge" and two TextViews with id
    // "title" and "snippet".
    /**
     * Window to be displayed
     */
    private final View mWindow;
    /**
     * Content to be displayed
     */
    private final View mContents;
    /**
     * TAG for logging purposes
     */
    private static final String TAG = "InfoWindowAdapterLog";

    /**
     * Constructor for the Info Window Adapter
     * @param context Context of the activity
     */
    public CustomInfoWindowAdapter(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        mWindow = inflater.inflate(R.layout.custom_info_window, null);
        mContents = inflater.inflate(R.layout.custom_info_contents, null);
    }

    /**
     * Getter for the infoWindow
     * @param marker Marker to display the infowindow at
     * @return InfoWindow to be displayed
     */
    @Override
    public View getInfoWindow(Marker marker) {
            /*
            if (mOptions.getCheckedRadioButtonId() != R.id.custom_info_window) {
                // This means that getInfoContents will be called.
                return null;
            }
            */
        render(marker, mWindow);
        return mWindow;
    }

    /**
     * Getter for infoContents
     * @param marker Marker to display the infoContents at
     * @return infoContents to be displayed
     */
    @Override
    public View getInfoContents(Marker marker) {
            /*
            if (mOptions.getCheckedRadioButtonId() != R.id.custom_info_contents) {
                // This means that the default info contents will be used.
                return null;
            }*/
        render(marker, mContents);
        return mContents;
    }

    /**
     * Renders the infoWindow
     * @param marker marker to render the infoWindow at
     * @param view infoWindow to be rendered
     */
    private void render(final Marker marker, View view) {

        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.title));
        if (title != null) {
            // Spannable string allows us to edit the formatting of the text.
            SpannableString titleText = new SpannableString(title);
//            titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
            titleUi.setText(titleText);
        } else {
            titleUi.setText("");
        }

        String snippet = marker.getSnippet();

        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        if (snippet != null && snippet.length() > 12) {
            SpannableString snippetText = new SpannableString(snippet);
//            snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 10, 0);
//            snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12, snippet.length(), 0);
            snippetUi.setText(snippetText);
        } else {
            snippetUi.setText("");
        }

    }


}
