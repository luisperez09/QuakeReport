package com.example.android.quakereport;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public static final String LOCATION_SEPARATOR = " of ";
    public static final String OFFSET_SEPARATOR = "km";

    /**
     * Constructs a new {@link EarthquakeAdapter}
     *
     * @param context     context of the app
     * @param earthquakes List of Earthquakes, which is the data source of the adapter
     */
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,
                    parent, false);
        }
        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String[] locationParts = splitLocation(currentEarthquake.getLocation());

        TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetTextView.setText(locationParts[0]);

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        locationTextView.setText(locationParts[1]);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        return timeFormat.format(dateObject);
    }

    /**
     * Splits original location into two sub-strings
     *
     * @param location original location
     * @return array of strings containing location sub-strings
     */
    private String[] splitLocation(String location) {
        String[] result = new String[2];
        if (location.contains(LOCATION_SEPARATOR)) {
            result = location.split(LOCATION_SEPARATOR);
            result[0] = getLocalizedOffset(result[0]);
        } else {
            result[0] = getContext().getString(R.string.near_the);
            result[1] = location;
        }
        return result;
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Localizes Offset distance and orientation (43km WNW of..)
     * This method ony gets called when the offset string contains "of"
     *
     * @param offsetString String of the distance and direction offset
     * @return Localized offset according to available locales in the app
     */
    private String getLocalizedOffset(String offsetString) {
        if (offsetString.contains(OFFSET_SEPARATOR)) {
            String[] offsetParts = new String[2];
            offsetParts = offsetString.split(OFFSET_SEPARATOR);
            String km = offsetParts[0];
            String direction = getLocalizedDirection(offsetParts[1]);
            return getContext().getString(R.string.friendly_location, km, direction);
        }
        // if string contains "of" but not "km" return original offset string in english
        return offsetString + LOCATION_SEPARATOR;
    }

    /**
     * Localizes earthquake direction according to available locales
     *
     * @param direction The direction in english
     * @return The localized direction if available in app
     */
    private String getLocalizedDirection(String direction) {
        switch (direction) {
            case " N":
                return getContext().getString(R.string.orientation_N);
            case " NE":
                return getContext().getString(R.string.orientation_NE);
            case " NW":
                return getContext().getString(R.string.orientation_NW);
            case " NNE":
                return getContext().getString(R.string.orientation_NNE);
            case " NNW":
                return getContext().getString(R.string.orientation_N);
            case " S":
                return getContext().getString(R.string.orientation_S);
            case " SE":
                return getContext().getString(R.string.orientation_SE);
            case " SW":
                return getContext().getString(R.string.orientation_SW);
            case " SSE":
                return getContext().getString(R.string.orientation_SSE);
            case " SSW":
                return getContext().getString(R.string.orientation_SSW);
            case " E":
                return getContext().getString(R.string.orientation_E);
            case " ENE":
                return getContext().getString(R.string.orientation_ENE);
            case " ESE":
                return getContext().getString(R.string.orientation_ESE);
            case " W":
                return getContext().getString(R.string.orientation_W);
            case " WNW":
                return getContext().getString(R.string.orientation_WNW);
            case " WSW":
                return getContext().getString(R.string.orientation_WSW);
        }
        return direction;
    }
}
