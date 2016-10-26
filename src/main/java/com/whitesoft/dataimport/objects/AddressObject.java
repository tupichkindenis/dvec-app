package com.whitesoft.dataimport.objects;

/**
 * Created by tupichkindenis on 25.10.16.
 */
public class AddressObject {
    private String index;
    private String region;
    private String district;
    private String settlement;
    private String street;
    private String building;
    private String room;

    public AddressObject(String index, String region, String district, String settlement, String street, String building, String room) {
        this.index = index;
        this.region = region;
        this.district = district;
        this.settlement = settlement;
        this.street = street;
        this.building = building;
        this.room = room;
    }


    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}

