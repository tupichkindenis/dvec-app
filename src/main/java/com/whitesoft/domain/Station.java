package com.whitesoft.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.whitesoft.domain.core.AbstractEntity;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.*;

/**
 * An Station.
 *
 * Created by Denis Tupichkin on 18.09.16.
 */
@Entity
@Table(name = "STATION")
public class Station extends AbstractEntity {

    @ManyToOne(optional = false)
    private Office office;

    @Column(nullable = false)
    private String name;

    private LegalAddress legalAddress;

    private GeoLocation geolocation;

    @ElementCollection
    @CollectionTable(
            name = "STATION_PHONES",
            joinColumns = @JoinColumn(name = "STATION_ID") )
    private List<PhoneNumber> phones = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "STATION_FAXES",
            joinColumns = @JoinColumn(name = "STATION_ID") )
    private List<FaxNumber> faxes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "STATION_EMAILS",
            joinColumns = @JoinColumn(name = "STATION_ID") )
    private List<EmailAddress> emails = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "STATION_WORKDAYS",
            joinColumns = @JoinColumn(name = "STATION_ID") )
    private List<Workday> workDays = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
        name = "STATION_PAYSYSTEMS",
        joinColumns = @JoinColumn(name = "STATION_ID") )
    private List<PaymentSystem> paymentSystems = new ArrayList<>();

    public Station(String name, Office office, LegalAddress legalAddress, GeoLocation geoLocation) {
        Assert.notNull(name);
        Assert.hasLength(name);
        Assert.notNull(office);
        Assert.notNull(legalAddress);

        this.setName(name);
        this.setOffice(office);
        this.setLegalAddress(legalAddress);
        this.setGeolocation(geoLocation);
    }

    protected Station() {

    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LegalAddress getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(LegalAddress legalAddress) {
        this.legalAddress = legalAddress;
    }

    public GeoLocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeoLocation geolocation) {
        this.geolocation = geolocation;
    }

    public List<Workday> getWorkDays() {
        return workDays;
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumber> phones) {
        this.phones = phones;
    }

    public List<FaxNumber> getFaxes() {
        return faxes;
    }

    public void setFaxes(List<FaxNumber> faxes) {
        this.faxes = faxes;
    }

    public List<EmailAddress> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailAddress> emails) {
        this.emails = emails;
    }

    public void setWorkDays(List<Workday> workDays) {
        this.workDays = workDays;
    }

    public List<PaymentSystem> getPaymentSystems() {
        return paymentSystems;
    }

    public void setPaymentSystems(List<PaymentSystem> paymentSystems) {
        this.paymentSystems = paymentSystems;
    }
}
