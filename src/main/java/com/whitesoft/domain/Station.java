package com.whitesoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.whitesoft.domain.core.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Setter @Getter
@NoArgsConstructor
public class Station extends AbstractEntity {

    @ManyToOne(optional = false)
    @JsonIgnore
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

    @Builder
    public Station(String name, Office office, LegalAddress legalAddress, GeoLocation geoLocation) {
        super(UUID.randomUUID());

        this.setName(name);
        this.setOffice(office);
        this.setLegalAddress(legalAddress);
        this.setGeolocation(geoLocation);
    }


}
