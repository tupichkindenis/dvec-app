package com.whitesoft.domain;import com.whitesoft.domain.core.AbstractEntity;import lombok.Builder;import lombok.Getter;import lombok.NoArgsConstructor;import lombok.Setter;import org.springframework.util.Assert;import javax.persistence.*;import java.util.*;/** * Сущность ОТДЕЛЕНИЕ. * Created by tupichkindenis on 18.09.16. */@Entity@Table(name = "OFFICE")@Getter @Setter@NoArgsConstructorpublic class Office extends AbstractEntity {    @Column(name = "NAME", nullable = false)    private String name;    @ManyToOne(optional = false)    private Branch branch;    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)    @JoinColumn(name = "OFFICE_ID")    private Set<Station> stations = new HashSet<>();    @ElementCollection    @CollectionTable( name = "PAYSYSTEMS", joinColumns = @JoinColumn(name = "OFFICE_ID") )    private List<PaymentSystem> paymentSystems = new ArrayList<>();    @Builder    public Office(final UUID id, final String name, final Branch branch, final List<PaymentSystem> paySystems) {        super(id);        this.setName(name);        this.setBranch(branch);        this.setPaymentSystems(paySystems);    }}