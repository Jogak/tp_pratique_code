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

    public void setFirstname(String firstname){ this.firstname = firstname; }

    @Column(name = "phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber){ this.phonenumber = phonenumber;}

    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname){ this.lastname = lastname; }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address){this.address = address;}

    @OneToOne
    private  CriminalRecordEntity idCriminalRecord;
}
