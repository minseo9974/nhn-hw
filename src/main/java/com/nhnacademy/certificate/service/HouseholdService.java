package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.HouseholdDto;
import com.nhnacademy.certificate.entity.Household;
import java.util.Optional;

public interface HouseholdService {
    HouseholdDto getHousehold(Integer householdSerialNumber);

    void deleteHouseholdAndSoOn(Integer householdSerialNumber);

    Household getHouseholdByResidentSerialNumber(Integer residentSerialNumber);
}
