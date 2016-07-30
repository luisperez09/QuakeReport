package com.example.android.quakereport;

/**
 * Handles {@link Earthquake} objects
 */
public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;

    /**
     * Constructs a new {@link Earthquake} object
     *
     * @param magnitude magnitude of earthquake
     * @param location  location of earthquake
     * @param timeInMilliseconds      timeInMilliseconds of earthquake
     */
    public Earthquake(String magnitude, String location, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
