package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "address")
public class AddressEntity {
    private UUID id;
    private int number;
    private String street;
    private TownEntity town;
    private Set<TargetEntity> targets;

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @ManyToOne
    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
    }

    @ManyToMany
    @JoinTable(name = "targetaddress", joinColumns = @JoinColumn(name = "address_uuid", referencedColumnName = "uuid"), inverseJoinColumns = @JoinColumn(name = "target_uuid", referencedColumnName = "uuid"))
    public Set<TargetEntity> getTargets() {
        return targets;
    }

    public void setTargets(Set<TargetEntity> targets) {
        this.targets = targets;
    }
}
