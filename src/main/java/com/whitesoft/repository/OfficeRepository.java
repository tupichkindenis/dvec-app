package com.whitesoft.repository;

import com.whitesoft.domain.Office;
import com.whitesoft.repository.projections.OfficeInlineProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * OfficeRepository
 * Created by tupichkindenis on 21.09.16.
 */
@Component
public interface OfficeRepository extends PagingAndSortingRepository<Office, UUID> {}

