package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.HouseholdDto;
import com.nhnacademy.certificate.entity.Household;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {

    @Query(
            "select new com.nhnacademy.certificate.domain.HouseholdDto(r.name,h.householdCompositionReasonCode,h.householdCompositionDate) " +
                    "from Household h join Resident r on h.resident.residentSerialNumber = r.residentSerialNumber " +
                    "where h.householdSerialNumber = :householdSerialNumber"
    )
    HouseholdDto findByPk(@Param("householdSerialNumber") Integer householdSerialNumber);


    @Query("select count(hc) from HouseholdCompositionResident hc where hc.pk.householdSerialNumber = :householdSerialNumber")
    Long countHouseholdCompositionByHouseholdSerialNumber(
            @Param("householdSerialNumber") Integer householdSerialNumber);
    @Transactional
    @Modifying
    @Query("delete from HouseholdMovementAddress h where h.pk.householdSerialNumber = :householdSerialNumber")
    void deleteHouseholdMovementAddressByHouseholdSerialNumber(
            @Param("householdSerialNumber") Integer householdSerialNumber);

    Optional<Household> findByResident_ResidentSerialNumber(Integer residentSerialNumber);
    @Transactional
    @Modifying
    @Query("delete from HouseholdCompositionResident h where h.pk.householdSerialNumber = :householdSerialNumber")
    void deleteHouseholdCompositionResidentByHouseholdSerialNumber(
            @Param("householdSerialNumber") Integer householdSerialNumber);
}