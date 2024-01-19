package com.nhnacademy.certificate.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "household")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number", nullable = false)
    private Integer householdSerialNumber;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @Column(name = "household_composition_date", nullable = false)
    private LocalDateTime householdCompositionDate;

    @Column(name = "household_composition_reason_code", length = 20, nullable = false)
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address", length = 500, nullable = false)
    private String currentHouseMovementAddress;
}
