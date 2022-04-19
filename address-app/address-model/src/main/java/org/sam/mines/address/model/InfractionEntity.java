package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "infraction")
public class InfractionEntity {
    private UUID id;
    private String place;
    private String mobile;
    private String description;
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }
    @Column
    public int getNumber() {
        return number;
    }

    /*
    public CondamnationEntity getComdamnation() {
        return comdamnation;
    }

    public CriminalRecordEntity getCriminalRecordEntity() {
        return criminalRecordEntity;
    }*/

    public void setId(UUID id) {
        this.id = id;
    }
    /*
    public void setComdamnation(CondamnationEntity comdamnation) {
        this.comdamnation = comdamnation;
    }

    public void setCriminalRecordEntity(CriminalRecordEntity criminalRecordEntity) {
        this.criminalRecordEntity = criminalRecordEntity;
    }
    */
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
    /*
    @OneToOne
    @JoinColumn(name = "condamnationid", referencedColumnName = "id")
    private CondamnationEntity comdamnation;

    @OneToOne
    @JoinColumn(name = "criminalrecordid", referencedColumnName = "id")
    private CriminalRecordEntity criminalRecordEntity;
    */

    public static final class InfractionBuilder {
        private UUID id;
        private String place;
        private String description;
        private String mobile;
        private int number;

        private InfractionBuilder() {
        }

        public static InfractionEntity.InfractionBuilder aInfraction() {
            return new InfractionBuilder();
        }

        public InfractionEntity.InfractionBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public InfractionEntity.InfractionBuilder withPlace(String place) {
            this.place = place;
            return this;
        }

        public InfractionEntity.InfractionBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public InfractionEntity.InfractionBuilder withMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public InfractionEntity.InfractionBuilder withNumber(int number) {
            this.number = number;
            return this;
        }

        public InfractionEntity build() {
            var infraction = new InfractionEntity();
            infraction.setId(id);
            infraction.setPlace(place);
            infraction.setNumber(number);
            infraction.setMobile(mobile);
            infraction.setDescription(description);
            return infraction;
        }
    }
}
