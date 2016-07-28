package com.example.android.quakereport;

/**
 * Handles {@link Earthquake} objects
 */
public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private String mDate;

    /**
     * Constructs a new {@link Earthquake} object
     *
     * @param magnitude magnitude of earthquake
     * @param location  location of earthquake
     * @param date      date of earthquake
     */
    public Earthquake(String magnitude, String location, String date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }
}
