package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.request.HouseholdMovementCreateRequest;
import com.nhnacademy.certificate.domain.request.HouseholdMovementUpdateRequest;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import com.nhnacademy.certificate.exception.HouseholdNotFoundedException;
import com.nhnacademy.certificate.service.HouseholdMovementAddressService;
import com.nhnacademy.certificate.service.HouseholdService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
public class HouseholdMovementRestController {
    private final HouseholdService householdService;
    private final HouseholdMovementAddressService householdMovementAddressService;

    public HouseholdMovementRestController(HouseholdService householdService,
                                           HouseholdMovementAddressService householdMovementAddressService) {
        this.householdService = householdService;
        this.householdMovementAddressService = householdMovementAddressService;
    }

    @PostMapping("/{householdSerialNumber}/movement")
    public void create(@RequestBody HouseholdMovementCreateRequest request, @PathVariable Integer householdSerialNumber) {
        Household household = householdService.getHouseholdWithEntity(householdSerialNumber);
        if (Objects.isNull(household)) {
            throw new HouseholdNotFoundedException();
        }
        String date = String.valueOf(request.getReportDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        HouseholdMovementAddress.Pk pk =
                new HouseholdMovementAddress.Pk(localDate, householdSerialNumber);
        householdMovementAddress.setHousehold(household);
        householdMovementAddress.setPk(pk);
        householdMovementAddress.setHouseMovementAddress(request.getMovementAdd());
        householdMovementAddress.setLastAddressYN(request.getLastAddress());

        householdMovementAddressService.saveMovement(householdMovementAddress);
    }

    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    public void update(@RequestBody HouseholdMovementUpdateRequest request, @PathVariable Integer householdSerialNumber,
                       @PathVariable Integer reportDate) {
        String date = String.valueOf(reportDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(localDate, householdSerialNumber);
        HouseholdMovementAddress householdMovementAddress = householdMovementAddressService.getHouseholdMovementAddress(pk);
        if (Objects.isNull(householdMovementAddress)) {
            throw new HouseholdNotFoundedException();
        }
        householdMovementAddress.setLastAddressYN(request.getLastAddress());

        householdMovementAddressService.saveMovement(householdMovementAddress);
    }

    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    public void delete(@PathVariable Integer householdSerialNumber, @PathVariable Integer reportDate) {
        String date = String.valueOf(reportDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(localDate, householdSerialNumber);
        if (!householdMovementAddressService.existsMovement(pk)) {
            throw new HouseholdNotFoundedException();
        }
        householdMovementAddressService.deleteMovement(pk);
    }
}
