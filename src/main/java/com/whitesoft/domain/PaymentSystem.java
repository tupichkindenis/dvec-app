package com.whitesoft.domain;

import com.whitesoft.domain.enums.PaymentSystems;

import javax.persistence.Embeddable;

/**
 * Created by tupichkindenis on 26.10.16.
 */
@Embeddable
public class PaymentSystem {
    private PaymentSystems name = PaymentSystems.CHRONOPAY;
    private Integer identifier;

    public PaymentSystem() {
    }

    public PaymentSystem(PaymentSystems name, Integer identifier) {
        this.name = name;
        this.identifier = identifier;
    }

    public PaymentSystems getName() {
        return name;
    }

    public Integer getIdentifier() {
        return identifier;
    }

}
