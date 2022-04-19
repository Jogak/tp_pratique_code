package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "criminalrecord")
public class CriminalRecordEntity {
    private UUID id;
    private String ref;
    private UUID idnumber;
    private String number;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setIdnumber(UUID idnumber) {
        this.idnumber = idnumber;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public void setCriminalRecordEntity(CriminalRecordEntity criminalRecordEntity) {
        this.criminalRecordEntity = criminalRecordEntity;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", unique = true, nullable = false)
    @Type(type = "pg-uuid")

    public UUID getId() {
        return id;
    }

    @Column(name = "ref")
    public String getRef() {
        return ref;
    }

    public void setRef(String ref){ this.ref = ref; }

    @OneToOne
    @JoinColumn(name = "idnumber")
    private PersonEntity person;

    @OneToOne
    private CriminalRecordEntity criminalRecordEntity;

}
