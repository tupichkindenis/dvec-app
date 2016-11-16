package com.whitesoft.domain.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.whitesoft.domain.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;
import java.util.UUID;

/**
 * StationStandardProjection
 * Created by tupichkindenis on 22.09.16.
 */
@Projection(name = "std", types = { Station.class })
@JsonPropertyOrder({
         "id"
        ,"name"
        ,"legalAddress"
        ,"geolocation"
        ,"phones"
        ,"faxes"
        ,"emails"
        ,"workDays"
        })
public interface StationStandardProjection {
    UUID getId();
    String getName();
    LegalAddress getLegalAddress();
    GeoLocation getGeolocation();
    List<PhoneNumber> getPhones();
    List<FaxNumber> getFaxes();
    List<EmailAddress> getEmails();
    List<Workday> getWorkDays();
}