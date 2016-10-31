package com.whitesoft.domain;

import com.whitesoft.domain.core.AbstractEntity;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Сущность ФИЛИАЛ.
 * Created by tupichkindenis on 18.09.16.
 */
@Entity
public class Branch extends AbstractEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    @JoinColumn(name = "BRANCH_ID")
    private Set<Office> offices = new HashSet<>();

    private Branch(){
    }

    /**
     * Ctor.
     * @param name
     */
    public Branch(UUID id, String name) {
        super(id);
        Assert.notNull(name);
        Assert.hasLength(name);
        this.setName(name);
    }


    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Set<Office> getOffices() {
        return offices;
    }

    /**
     * Add new office.
     * @param office
     */
    public void addOffice(Office office) {
        Assert.notNull(office);
        this.offices.add(office);
    }
}
