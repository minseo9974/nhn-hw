package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.BirthDto;
import com.nhnacademy.certificate.domain.DeathDto;
import com.nhnacademy.certificate.domain.FamilyDto;
import com.nhnacademy.certificate.domain.IdCardDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.domain.ResidentOwnerDto;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.custom.ResidentCustom;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ResidentRepository extends JpaRepository<Resident, Integer>, ResidentCustom {
    Page<ResidentDto> getAllBy(Pageable pageable);

    ResidentOwnerDto findByResidentSerialNumber(Integer residentSerialNumber);

    BirthDto findBirthByResidentSerialNumber(Integer residentSerialNumber);

    DeathDto findDeathByResidentSerialNumber(Integer residentSerialNumber);


    @Query(
            "select new com.nhnacademy.certificate.domain.IdCardDto(h.householdRelationshipCode,r.name,r.residentRegistrationNumber,h.reportDate,h.householdCompositionChangeReasonCode) " +
                    "from Resident r left JOIN HouseholdCompositionResident h on r.residentSerialNumber = h.pk.residentSerialNumber " +
                    "where h.pk.householdSerialNumber = :householdSerialNumber"
    )
    List<IdCardDto> findByPkWithHousehold(@Param("householdSerialNumber") Integer householdSerialNumber);

    @Query(
            "select new com.nhnacademy.certificate.domain.FamilyDto(f.familyRelationshipCode, r.name,r.birthDate,r.residentRegistrationNumber,r.genderCode)" +
                    "from Resident r left join FamilyRelationship f on r.residentSerialNumber = f.pk.familyResidentSerialNumber " +
                    "where f.familyRelationshipCode not in ('형제','자매') " +
                    "and f.pk.baseResidentSerialNumber = :residentSerialNumber"
    )
    List<FamilyDto> getFamilyDto(@Param("residentSerialNumber") Integer residentSerialNumber);

    @Transactional
    @Modifying
    @Query("delete from BirthDeathReportResident  b where b.pk.residentSerialNumber = :residentSerialNumber or b.reportResidentSerialNumber = :residentSerialNumber")
    void deleteBirthDeathReportByResidentSerialNumber(@Param("residentSerialNumber") Integer residentSerialNumber);

    @Transactional
    @Modifying
    @Query("delete from HouseholdCompositionResident h where h.pk.residentSerialNumber = :residentSerialNumber")
    void deleteHouseholdCompositionByResidentSerialNumber(@Param("residentSerialNumber") Integer residentSerialNumber);

    @Transactional
    @Modifying
    @Query("delete from FamilyRelationship f where f.pk.baseResidentSerialNumber = :residentSerialNumber or f.pk.familyResidentSerialNumber = :residentSerialNumber")
    void deleteFamilyRelationshipsByResidentSerialNumber(@Param("residentSerialNumber") Integer residentSerialNumber);

    @Transactional
    @Modifying
    @Query("delete from CertificateIssue c where c.resident.residentSerialNumber = :residentSerialNumber")
    void deleteCertificateIssueByResidentSerialNumber(@Param("residentSerialNumber") Integer residentSerialNumber);



}