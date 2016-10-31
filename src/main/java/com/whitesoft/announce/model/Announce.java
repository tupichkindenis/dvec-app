package com.whitesoft.announce.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность "Объявление".
 *
 * Created by Tupichkin Denis on 31.10.16.
 */
//@Entity
public class Announce {
    /**
     *  Уникальный идентификатор объявления.
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     *  Дата создания объявления.
     */
    private Date createdAt;

    /**
     *  Автор объявления.
     */
    private String creator;

    /**
     *  Текст объявления.
     */
//    @Column(name = "TEXT", length = 256)
    private String text;

    private Announce(){}

    public Announce(String creator, Date createdAt, String text){
        this.creator = creator;
        this.createdAt = createdAt;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCreator() {
        return creator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
