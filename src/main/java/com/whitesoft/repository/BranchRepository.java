package com.whitesoft.repository;

import com.whitesoft.domain.Branch;
import com.whitesoft.repository.projections.BranchInlineProjection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by tupichkindenis on 21.09.16.
 */
@Component
public interface BranchRepository extends PagingAndSortingRepository<Branch, UUID> {

    @Cacheable(value="branchCache", key="#name")
    List<Branch> findByName(@Param("name") String name);

}

