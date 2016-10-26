package com.whitesoft.dataimport.objects;

import com.whitesoft.dataimport.objects.*;

import java.util.List;

/**
 * Created by tupichkindenis on 25.10.16.
 */
public class StationObject {

    private BranchObject branch;
    private OfficeObject office;
    private String name;
    private AddressObject address;
    private LocationObject location;
    private List<String> phones;
    private List<String> faxes;
    private List<String> emails;
    private List<ScheduleDayObject> schedule;
    private PaymentObject payment;


    public BranchObject getBranch() { return branch;}

    public void setBranch(BranchObject branch) {
        this.branch = branch;
    }

    public OfficeObject getOffice() {
        return office;
    }

    public void setOffice(OfficeObject office) {
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressObject getAddress() {
        return address;
    }

    public void setAddress(AddressObject address) {
        this.address = address;
    }

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getFaxes() {
        return faxes;
    }

    public void setFaxes(List<String> faxes) {
        this.faxes = faxes;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<ScheduleDayObject> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleDayObject> schedule) {
        this.schedule = schedule;
    }

    public PaymentObject getPayment() {
        return payment;
    }

    public void setPayment(PaymentObject payment) {
        this.payment = payment;
    }
}
