package com.whitesoft.domain;

import com.whitesoft.domain.core.AbstractEntity;
import lombok.*;
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
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Branch extends AbstractEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private Set<Office> offices = new HashSet<>();

    @Builder
    public Branch(final UUID id, final String name, final Set<Office> offices){
        super(id);
        this.name = name;
        this.offices = offices;
    }

}


