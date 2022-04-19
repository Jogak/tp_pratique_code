package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "condamnation")
public class CondamnationEntity {
    private UUID id;
    private int comdamnationDurationDay;
    private int reprieveDurationDay;
    private float fine;
    private int number;

    @Column
    public int getNumber() {
        return number;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setComdamnationDurationDay(int comdamnationDurationDay) {
        this.comdamnationDurationDay = comdamnationDurationDay;
    }

    public void setInfractionEntity(InfractionEntity infractionEntity) {
        this.infractionEntity = infractionEntity;
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
    public InfractionEntity getInfractionEntity() {
        return infractionEntity;
    }

    @OneToOne
    private InfractionEntity infractionEntity;

    public static final class CondamnationBuilder {
        private UUID id;
        private int comdamnationDurationDay;
        private int reprieveDurationDay;
        private float fine;
        private int number;

        private CondamnationBuilder() {
        }

        public static CondamnationEntity.CondamnationBuilder aCondamnation() {
            return new CondamnationBuilder();
        }

        public CondamnationEntity.CondamnationBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public CondamnationEntity.CondamnationBuilder withCondamnationDurationDay(int comdamnationDurationDay) {
            this.comdamnationDurationDay = comdamnationDurationDay;
            return this;
        }

        public CondamnationEntity.CondamnationBuilder withReprieveDurationDay(int reprieveDurationDay) {
            this.reprieveDurationDay = reprieveDurationDay;
            return this;
        }

        public CondamnationEntity.CondamnationBuilder withFine(float fine) {
            this.fine = fine;
            return this;
        }

        public CondamnationEntity.CondamnationBuilder withNumber(int number) {
            this.number = number;
            return this;
        }

        public CondamnationEntity build() {
            var condamnationEntity = new CondamnationEntity();
            condamnationEntity.setId(id);
            condamnationEntity.setComdamnationDurationDay(comdamnationDurationDay);
            condamnationEntity.setReprieveDurationDay(reprieveDurationDay);
            condamnationEntity.setFine(fine);
            condamnationEntity.setNumber(number);
            return condamnationEntity;
        }
    }

}
