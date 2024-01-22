package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.request.HouseholdCreateRequest;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.HouseholdAllReadyExists;
import com.nhnacademy.certificate.exception.HouseholdNotFoundedException;
import com.nhnacademy.certificate.service.HouseholdService;
import com.nhnacademy.certificate.service.ResidentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
public class HouseholdRestController {

    private final ResidentService residentService;
    private final HouseholdService householdService;

    public HouseholdRestController(ResidentService residentService, HouseholdService householdService) {
        this.residentService = residentService;
        this.householdService = householdService;
    }

    @PostMapping
    public void create(@RequestBody HouseholdCreateRequest request) {
        if (householdService.exists(request.getHouseholdResidentSerialNumber())) {
            throw new HouseholdAllReadyExists();
        }
        Resident resident = residentService.getResidentById(request.getHouseholdResidentSerialNumber());
        Household household = new Household();
        household.setHouseholdSerialNumber(request.getHouseholdSerialNumber());
        household.setResident(resident);
        household.setHouseholdCompositionDate(request.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(request.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(request.getCurrentHouseMovementAddress());

        householdService.saveHousehold(household);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public void delete(@PathVariable Integer householdSerialNumber) {
        if (!householdService.exists(householdSerialNumber)) {
            throw new HouseholdNotFoundedException();
        }
        householdService.deleteHousehold(householdSerialNumber);

    }
}
