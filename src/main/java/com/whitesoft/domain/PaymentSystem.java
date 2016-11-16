package com.whitesoft.domain;

import com.whitesoft.domain.enums.PaymentSystems;
import lombok.*;

import javax.persistence.Embeddable;

/**
 * Created by tupichkindenis on 26.10.16.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PaymentSystem {
    @Setter(value = AccessLevel.NONE) private PaymentSystems type = PaymentSystems.CHRONOPAY;
    @Setter(value = AccessLevel.NONE) private String prefix;
    @Setter(value = AccessLevel.NONE) private String suffix;

    @Builder
    public PaymentSystem(final PaymentSystems type, final String prefix, final String suffix ){
        this.type = type;
        this.prefix = prefix;
        this.suffix = suffix;
    }
}
