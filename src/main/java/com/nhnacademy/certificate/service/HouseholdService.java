package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.HouseholdDto;
import com.nhnacademy.certificate.entity.Household;
import java.util.Optional;

public interface HouseholdService {
    HouseholdDto getHousehold(Integer householdSerialNumber);

    void deleteHouseholdAndSoOn(Integer householdSerialNumber);

    Household getHouseholdByResidentSerialNumber(Integer residentSerialNumber);

    void saveHousehold(Household household);

    void deleteHousehold(Integer householdSerialNumber);

    boolean exists(Integer householdSerialNumber);

    Household getHouseholdWithEntity(Integer householdSerialNumber);

}
