package com.nhnacademy.certificate.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "household")
@Data
public class Household {
    @Id
    @Column(name = "household_serial_number", nullable = false)
    private Integer householdSerialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @Column(name = "household_composition_date", nullable = false)
    private LocalDate householdCompositionDate;

    @Column(name = "household_composition_reason_code", length = 20, nullable = false)
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address", length = 500, nullable = false)
    private String currentHouseMovementAddress;
}
