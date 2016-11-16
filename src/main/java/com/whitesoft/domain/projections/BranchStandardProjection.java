package com.whitesoft.domain.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.whitesoft.domain.Branch;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

/**
 * BranchStandardProjection
 * Created by tupichkindenis on 22.09.16.
 */
@Projection(name = "std", types = { Branch.class })
@JsonPropertyOrder({"id","name"})
public interface BranchStandardProjection {
    UUID getId();
    String getName();
}
