package com.whitesoft.repository.projections;

import com.whitesoft.domain.Branch;
import com.whitesoft.domain.Office;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

/**
 * BranchInlineProjection
 * Created by tupichkindenis on 22.09.16.
 */
@Projection(name = "BranchInlineProjection", types = { Branch.class })
public interface BranchInlineProjection {
    UUID getId();
    String getName();
}
