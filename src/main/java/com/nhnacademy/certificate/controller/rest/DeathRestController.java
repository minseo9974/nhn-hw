package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.request.BirthDeathCreateRequest;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.BirthDeathReportResidentAllreadyExists;
import com.nhnacademy.certificate.service.BirthDeathReportResidentService;
import com.nhnacademy.certificate.service.ResidentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class DeathRestController {
    private final BirthDeathReportResidentService birthDeathReportResidentService;
    private final ResidentService residentService;

    public DeathRestController(BirthDeathReportResidentService birthDeathReportResidentService,
                               ResidentService residentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
        this.residentService = residentService;
    }


    @PostMapping("/{serialNumber}/death")
    public void create(@RequestBody BirthDeathCreateRequest request,
                       @PathVariable Integer serialNumber) {
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        Resident resident = residentService.getResidentById(request.getBirthSerialNum());
        BirthDeathReportResident.Pk pk =
                new BirthDeathReportResident.Pk(request.getBirthSerialNum(), request.getBirthType());
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setResident(resident);
        birthDeathReportResident.setReportResidentSerialNumber(serialNumber);
        birthDeathReportResident.setBirthDeathReportDate(request.getBirthDate().atStartOfDay());
        birthDeathReportResident.setBirthReportQualificationsCode(request.getBirthQualification());
        birthDeathReportResident.setEmailAddress(request.getEmail());
        birthDeathReportResident.setPhoneNumber(request.getPhone());
        if (birthDeathReportResidentService.exist(birthDeathReportResident)) {
            throw new BirthDeathReportResidentAllreadyExists();
        }
        birthDeathReportResidentService.saveBD(birthDeathReportResident);
    }
    @PutMapping("/{reportSerialNumber}/death/{birthSerialNumber}")
    public void update(@RequestBody BirthDeathCreateRequest request,
                       @PathVariable Integer reportSerialNumber, @PathVariable Integer deathSerialNumber) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(deathSerialNumber, "사망");
        BirthDeathReportResident bd = birthDeathReportResidentService.getBD(pk);
        bd.setPhoneNumber(request.getPhone());
        bd.setEmailAddress(request.getEmail());

        birthDeathReportResidentService.saveBD(bd);

    }

    @DeleteMapping("/{reportSerialNumber}/death/{deathSerialNumber}")
    public void delete(@PathVariable Integer reportSerialNumber, @PathVariable Integer deathSerialNumber) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(deathSerialNumber, "사망");
        BirthDeathReportResident bd = birthDeathReportResidentService.getBD(pk);

        birthDeathReportResidentService.deleteBD(bd);
    }

}
