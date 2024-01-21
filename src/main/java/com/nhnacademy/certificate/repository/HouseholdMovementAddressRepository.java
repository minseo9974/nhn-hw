package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.AddressDto;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseholdMovementAddressRepository
        extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {

    @Query(
            "select new com.nhnacademy.certificate.domain.AddressDto(a.lastAddressYN,a.houseMovementAddress,a.pk.houseMovementReportDate)" +
                    "from HouseholdMovementAddress a " +
                    "where a.pk.householdSerialNumber = :householdSerialNumber " +
                    "order by a.pk.houseMovementReportDate desc"
    )
    List<AddressDto> getAddressList(@Param("householdSerialNumber") Integer householdSerialNumber);
}