package com.whitesoft.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embeddable;

/**
 * LegalAddress
 * Created by tupichkindenis on 21.09.16.
 */
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegalAddress {

    private String index;

    private String region;

    private String district;

    private String settlement;

    private String street;

    private String building;

    private String room;

    /**
     *
     * @param index
     * @param region
     * @param district
     * @param settlement
     * @param street
     * @param building
     */
    public LegalAddress(String index, String region, String district, String settlement, String street, String building, String room) {
        this.index = index;
        this.region = region;
        this.district = district;
        this.settlement = settlement;
        this.street = street;
        this.building = building;
        this.setRoom(room);
    }

    protected LegalAddress() {}

    /**
     *
     * @return
     */
    public String getIndex() {
        return index;
    }

    /**
     *
     * @return
     */
    public String getRegion() {
        return region;
    }

    /**
     *
     * @return
     */
    public String getDistrict() {
        return district;
    }

    /**
     *
     * @return
     */
    public String getSettlement() {
        return settlement;
    }

    /**
     *
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     *
     * @return
     */
    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }

    /**
     *
     * @param room
     */
    public void setRoom(String room) {
        this.room = room;
    }
}
