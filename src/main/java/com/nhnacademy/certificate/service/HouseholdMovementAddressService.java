package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.AddressDto;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import java.util.List;

public interface HouseholdMovementAddressService {
    List<AddressDto> getAddressList(Integer householdSerialNumber);

    void saveMovement(HouseholdMovementAddress householdMovementAddress);

    void deleteMovement(HouseholdMovementAddress.Pk pk);

    boolean existsMovement(HouseholdMovementAddress.Pk pk);

    HouseholdMovementAddress getHouseholdMovementAddress(HouseholdMovementAddress.Pk pk);
}
