package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "person")
public class CriminalRecordEntity {
    private UUID id;
    private String ref;
    private UUID idnumber;


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

    @Column(name = "ref")
    public String getRef() {
        return ref;
    }

    public void setRef(String ref){ this.ref = ref; }

    @OneToOne
    @JoinColumn(name = "idnumber")
    private PersonEntity person;


}
