package com.nhnacademy.certificate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "resident")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_serial_number", nullable = false)
    private Integer residentSerialNumber;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "resident_registration_number", length = 300, nullable = false)
    private String residentRegistrationNumber;

    @Column(name = "gender_code", length = 20, nullable = false)
    private String genderCode;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code", length = 20, nullable = false)
    private String birthPlaceCode;

    @Column(name = "registration_base_address", length = 500, nullable = false)
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code", length = 20)
    private String deathPlaceCode;

    @Column(name = "death_place_address", length = 500)
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "resident")
    private List<CertificateIssue> certificateIssues;

    @OneToMany(mappedBy = "resident")
    private List<BirthDeathReportResident> birthDeathReportResidents;

    @OneToMany(mappedBy = "resident")
    private List<HouseholdCompositionResident> householdCompositionResidents;

    @OneToMany(mappedBy = "resident")
    private List<FamilyRelationship> familyRelationships;
}
