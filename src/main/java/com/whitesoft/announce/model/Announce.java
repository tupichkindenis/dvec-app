package com.whitesoft.announce.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.whitesoft.domain.core.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность "Объявление".
 *
 * Created by Tupichkin Denis on 31.10.16.
 */
@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "announce")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonPropertyOrder({"id","createTime","author","header","text"})
public class Announce extends AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    @Setter(value = AccessLevel.NONE)
    @JsonFormat()
    private Date createTime = new Date();

    @Column(name = "author", length = 128, nullable = false)
    private String author;

    @Column(name = "header", length = 256, nullable = false)
    private String header;

    @Column(name = "text", length = 2000)
    private String text;

    @Override
    public String toString() {
        return "Announce{" +
                 " announceId=" + getId().toString() +
                ", createTime=" + createTime +
                ", author='"    + author + '\'' +
                ", header='"    + header + '\'' +
                ", text='"      + text + '\'' +
                '}';
    }
}
