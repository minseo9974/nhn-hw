package com.nhnacademy.certificate.service.impl;

import com.nhnacademy.certificate.domain.AddressDto;
import com.nhnacademy.certificate.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.certificate.service.HouseholdMovementAddressService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HouseholdMovementAddressServiceImpl implements HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    public HouseholdMovementAddressServiceImpl(HouseholdMovementAddressRepository householdMovementAddressRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
    }

    @Override
    public List<AddressDto> getAddressList(Integer householdSerialNumber) {
        return householdMovementAddressRepository.getAddressList(householdSerialNumber);
    }
}
