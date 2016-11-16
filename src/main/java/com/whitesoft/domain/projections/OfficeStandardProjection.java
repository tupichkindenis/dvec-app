package com.whitesoft.domain.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.whitesoft.domain.Office;
import com.whitesoft.domain.PaymentSystem;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;
import java.util.UUID;

/**
 * OfficeStandardProjection
 * Created by tupichkindenis on 22.09.16.
 */
@Projection(name = "std", types = { Office.class })
@JsonPropertyOrder(
                {"id"
                ,"name"
                ,"paymentSystems"})
public interface OfficeStandardProjection {
    UUID getId();
    String getName();
    List<PaymentSystem> getPaymentSystems();
}

