package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.AddressDto;
import java.util.List;

public interface HouseholdMovementAddressService {
    List<AddressDto> getAddressList(Integer householdSerialNumber);
}
