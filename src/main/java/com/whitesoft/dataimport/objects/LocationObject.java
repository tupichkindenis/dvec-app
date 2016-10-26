package com.whitesoft.dataimport.objects;

/**
 * Created by tupichkindenis on 25.10.16.
 */
public class LocationObject {
    private double latitude;
    private double longitude;

    public LocationObject(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
