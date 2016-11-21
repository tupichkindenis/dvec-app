package com.whitesoft.announce.repository;

import com.whitesoft.announce.model.Announce;
import com.whitesoft.announce.model.AnnounceStatus;
import com.whitesoft.announce.model.projections.AnnounceStandardProjection;
import com.whitesoft.domain.Branch;
import com.whitesoft.domain.Office;
import com.whitesoft.domain.projections.BranchStandardProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@RepositoryRestResource(excerptProjection = AnnounceStandardProjection.class)
public interface AnnounceRepository extends PagingAndSortingRepository<Announce, UUID> {
}
