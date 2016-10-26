package com.whitesoft.repository.projections;

import com.whitesoft.domain.Branch;
import com.whitesoft.domain.Office;
import com.whitesoft.domain.Station;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

/**
 * OfficeInlineProjection
 * Created by tupichkindenis on 22.09.16.
 */
@Projection(name = "OfficeInlineProjection", types = { Office.class })
public interface OfficeInlineProjection {
    UUID getId();
    String getName();
    Branch getBranch();
    UUID getBranchId();
}

