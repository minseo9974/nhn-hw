package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.service.HouseholdService;
import com.nhnacademy.certificate.service.ResidentService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/delete")
public class DeleteController {
    private final HouseholdService householdService;
    private final ResidentService residentService;

    public DeleteController(HouseholdService householdService, ResidentService residentService) {
        this.householdService = householdService;
        this.residentService = residentService;
    }


    @GetMapping("/{residentSerialNumber}")
    public String delete(@PathVariable Integer residentSerialNumber, Model model) {
        Household household = householdService.getHouseholdByResidentSerialNumber(residentSerialNumber);

        if (Objects.nonNull(household)) {
            householdService.deleteHouseholdAndSoOn(household.getHouseholdSerialNumber());
        }

        residentService.deleteResidentAndSoOn(residentSerialNumber);

        return "redirect:/";
    }
}
