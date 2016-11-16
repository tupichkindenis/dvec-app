package com.whitesoft.announce.api.mappers;

import com.whitesoft.announce.api.dto.AnnounceDTO;
import com.whitesoft.announce.model.Announce;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by tupichkindenis on 15.11.16.
 */
@Mapper
public interface AnnounceMapper {

  AnnounceMapper INSTANCE = Mappers.getMapper(AnnounceMapper.class);

  @Mapping(target = "id", expression = "java(announce.getId().toString())")
  @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  AnnounceDTO announceToAnnounceDto(Announce announce);
}
