package com.nhnacademy.certificate.service.impl;

import com.nhnacademy.certificate.domain.AddressDto;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
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

    @Override
    public void saveMovement(HouseholdMovementAddress householdMovementAddress) {
        householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public void deleteMovement(HouseholdMovementAddress.Pk pk) {
        householdMovementAddressRepository.deleteById(pk);
    }

    @Override
    public boolean existsMovement(HouseholdMovementAddress.Pk pk) {
        return householdMovementAddressRepository.existsById(pk);
    }

    @Override
    public HouseholdMovementAddress getHouseholdMovementAddress(HouseholdMovementAddress.Pk pk) {
        return householdMovementAddressRepository.findById(pk).orElse(null);
    }
}
