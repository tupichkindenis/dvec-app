package com.whitesoft.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GeoLocation
 * Created by tupichkindenis on 21.09.16.
 */
@Embeddable
public class GeoLocation {

    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

    /**
     *
     * @param latitude
     * @param longitude
     */
    public GeoLocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     */
    protected GeoLocation(){}

    /**
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @return
     */
    public double getLatitude() {
        return latitude;
    }
}
