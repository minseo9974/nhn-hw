package com.nhnacademy.certificate.service.impl;

import com.nhnacademy.certificate.domain.HouseholdDto;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.repository.HouseholdRepository;
import com.nhnacademy.certificate.service.HouseholdService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public HouseholdDto getHousehold(Integer householdSerialNumber) {
        return householdRepository.findByPk(householdSerialNumber);
    }


    @Override
    public void deleteHouseholdAndSoOn(Integer householdSerialNumber) {
        long familyCount = householdRepository.countHouseholdCompositionByHouseholdSerialNumber(householdSerialNumber);

        if (familyCount == 1) {
            householdRepository.deleteById(householdSerialNumber);
            householdRepository.deleteHouseholdMovementAddressByHouseholdSerialNumber(householdSerialNumber);
            householdRepository.deleteHouseholdCompositionResidentByHouseholdSerialNumber(householdSerialNumber);
        } else {
            throw new IllegalStateException("세대원이 있어 삭제가 불가능합니다.");
        }

    }

    @Override
    public Household getHouseholdByResidentSerialNumber(Integer residentSerialNumber) {
        return householdRepository.findByResident_ResidentSerialNumber(residentSerialNumber).orElse(null);
    }

    @Override
    public void saveHousehold(Household household) {
        householdRepository.save(household);
    }

    @Override
    public void deleteHousehold(Integer householdSerialNumber) {
        householdRepository.deleteById(householdSerialNumber);

    }

    @Override
    public boolean exists(Integer householdSerialNumber) {
        return householdRepository.existsById(householdSerialNumber);
    }

    @Override
    public Household getHouseholdWithEntity(Integer householdSerialNumber) {
        return householdRepository.findById(householdSerialNumber).orElse(null);
    }


}
