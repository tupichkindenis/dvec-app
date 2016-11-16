package com.whitesoft.dataimport.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by tupichkindenis on 25.10.16.
 */
@Setter @Getter
@NoArgsConstructor
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
}
