package com.example.android.quakereport;

/**
 * Handles {@link Earthquake} objects
 */
public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    /**
     * Constructs a new {@link Earthquake} object
     *
     * @param magnitude          magnitude of earthquake
     * @param location           location of earthquake
     * @param timeInMilliseconds timeInMilliseconds (from Epoch) of earthquake
     * @param url                url address for details of the earthquake
     */
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
