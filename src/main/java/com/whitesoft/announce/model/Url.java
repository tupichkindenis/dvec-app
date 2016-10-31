package com.whitesoft.announce.model;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;

/**
 * Created by tupichkindenis on 31.10.16.
 */
//@Entity(name = "AnnounceUrl")
public class Url {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String display_url;

    private String expanded_url;

    private Integer indices;

}
