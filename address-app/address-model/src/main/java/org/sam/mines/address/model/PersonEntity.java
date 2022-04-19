package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "person")
public class PersonEntity {
    private UUID id;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String address;


    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIdCriminalRecord(CriminalRecordEntity idCriminalRecord) {
        this.idCriminalRecord = idCriminalRecord;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

   @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }



    @Column(name = "phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }



    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }



    @Column(name = "address")
    public String getAddress() {
        return address;
    }



    @OneToOne
    private  CriminalRecordEntity idCriminalRecord;
}
