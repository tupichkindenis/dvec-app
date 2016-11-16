package com.whitesoft.dataimport.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * Created by tupichkindenis on 25.10.16.
 */
@Setter
@Getter
public class PaymentObject {
    private ChronopayMethodObject chronopay = new ChronopayMethodObject();
}
