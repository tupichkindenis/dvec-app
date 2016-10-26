package com.whitesoft.repository.projections;

import com.whitesoft.domain.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;
import java.util.UUID;

/**
 * StationInlineProjection
 * Created by tupichkindenis on 22.09.16.
 */
@Projection(name = "StationInlineProjection", types = { Station.class })
interface StationInlineProjection {
    UUID getId();
    Office getOffice();
    String getName();
    LegalAddress getLegalAddress();
    GeoLocation getGeolocation();
    Set<PhoneNumber> getPhones();
    Set<FaxNumber> getFaxes();
    Set<EmailAddress> getEmails();
    Set<Workday> getWorkDays();
}