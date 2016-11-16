package com.whitesoft.announce.repository;

import com.whitesoft.announce.model.Announce;
import com.whitesoft.announce.model.AnnounceStatus;
import com.whitesoft.domain.Branch;
import com.whitesoft.domain.Office;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 *
 * Created by tupichkindenis on 31.10.16.
 */
@Component
@RepositoryRestResource
public interface AnnounceRepository extends PagingAndSortingRepository<Announce, UUID> {

    List<Announce> findByStatus(AnnounceStatus status);

}
