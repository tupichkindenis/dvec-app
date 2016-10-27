package com.whitesoft.repository;

import com.whitesoft.domain.Station;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * StationRepository.
 * Created by tupichkindenis on 21.09.16.
 */
@Component
@RepositoryRestResource
public interface StationRepository extends PagingAndSortingRepository<Station, UUID> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(UUID aLong);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Station order);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Iterable<? extends Station> stations);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAll();

     Page<Station> findAll(Pageable pageable);


    List<Station> findByName(@Param("name") String name);
}