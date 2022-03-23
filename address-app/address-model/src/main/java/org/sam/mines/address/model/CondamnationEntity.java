package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Condamnation")
public class CondamnationEntity {
    private UUID id;
    private int comdamnationDurationDay;
    private int reprieveDurationDay;
    private float fine;

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

    @Column(name = "comdamnationdurationday")
    public int getComdamnationDurationDay() {
        return comdamnationDurationDay;
    }

    public void setNumber(int number) {
        this.comdamnationDurationDay = number;
    }

    @Column(name = "reprievedurationday")
    public int getReprieveDurationDay() {
        return reprieveDurationDay;
    }

    public void setReprieveDurationDay(int number) {
        this.reprieveDurationDay = number;
    }

    @Column(name = "fine")
    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }


    @OneToOne
    private InfractionEntity infractionEntity;
}
