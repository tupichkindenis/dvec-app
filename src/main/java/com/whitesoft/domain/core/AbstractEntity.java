package com.whitesoft.domain.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Base class to derive entity classes from.
 *
 * Created by tupichkindenis on 21.09.16.
 */
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public class AbstractEntity {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = false, length = 36, precision = 0)
    private UUID id = UUID.randomUUID();

    public AbstractEntity(){}


    public AbstractEntity(UUID id){
        this.id=id;
    }

    /**
     *
     * @return
     */
    public UUID getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }
}