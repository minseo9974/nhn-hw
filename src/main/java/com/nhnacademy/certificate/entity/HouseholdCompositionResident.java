package com.nhnacademy.certificate.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @MapsId("residentSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Column(name = "household_relationship_code", length = 20, nullable = false)
    private String householdRelationshipCode;

    @Column(name = "household_composition_change_reason_code", length = 20, nullable = false)
    private String householdCompositionChangeReasonCode;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "household_serial_number", nullable = false)
        private Integer householdSerialNumber;

        @Column(name = "resident_serial_number", nullable = false)
        private Integer residentSerialNumber;
    }
}
