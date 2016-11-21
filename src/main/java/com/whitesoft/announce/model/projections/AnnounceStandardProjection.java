package com.whitesoft.announce.model.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.whitesoft.announce.model.Announce;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.UUID;

@Projection(name = "std", types = {Announce.class})
@JsonPropertyOrder({"id","createTime","author","header","text"})
public interface AnnounceStandardProjection {
    UUID getId();
    Date getCreateTime();
    String getAuthor();
    String getHeader();
    String getText();
}
