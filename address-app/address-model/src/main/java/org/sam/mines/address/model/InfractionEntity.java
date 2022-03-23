package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Infraction")
public class InfractionEntity {
    private UUID id;
    private String place;
    private String mobile;
    private String description;



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

    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    @JoinColumn(name = "condamnationid", referencedColumnName = "id")
    private CondamnationEntity comdamnation;

    @Column
    @JoinColumn(name = "criminalrecordid", referencedColumnName = "id")
    private CriminalRecordEntity criminalRecordEntity;

}
