package com.whitesoft.announce.api.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tupichkindenis on 16.11.16.
 */
@Getter @Setter
@NoArgsConstructor
public class AnnounceListDTO {

    @Setter(value = AccessLevel.NONE)
    private Integer count = new Integer(0);

    @Setter(value = AccessLevel.NONE)
    private List<AnnounceDTO> items = new ArrayList<>();

    public AnnounceListDTO(final List<AnnounceDTO> items){
        this.items = items;
        count = items.size();
    }
}
