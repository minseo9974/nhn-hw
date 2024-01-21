package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.FamilyDto;
import com.nhnacademy.certificate.domain.ResidentOwnerDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.service.CertificateIssueService;
import com.nhnacademy.certificate.service.ResidentService;
import com.nhnacademy.certificate.util.GenerateRandom16Digits;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/family")
public class FamilyController {
    private final CertificateIssueService certificateIssueService;
    private final ResidentService residentService;

    public FamilyController(CertificateIssueService certificateIssueService, ResidentService residentService) {
        this.certificateIssueService = certificateIssueService;
        this.residentService = residentService;
    }

    @GetMapping("/view/{residentSerialNumber}")
    public String familyCertificate(@PathVariable Integer residentSerialNumber, Model model) {
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Seoul"));
        GenerateRandom16Digits makeCertificate = new GenerateRandom16Digits();
        long confirmNumber = makeCertificate.getNumber();
        String resultString = makeCertificate.getString(confirmNumber);

        model.addAttribute("localDate", date.toString());
        model.addAttribute("confirmNumber", resultString);
        // 증명서 발급 기록 등록 로직
        Resident resident = residentService.getResidentById(residentSerialNumber);
        if (Objects.isNull(resident)) {
            throw new ResidentNotFoundException();
        }
        certificateIssueService.saveCertificate(
                new CertificateIssue(confirmNumber, resident, "가족관계증명서", date));

        // 본인
        ResidentOwnerDto owner = residentService.getOwner(residentSerialNumber);
        model.addAttribute("owner", owner);

        // 가족관계증명서 가족 구성원
        List<FamilyDto> familyList = residentService.getFamilyDto(residentSerialNumber);

        model.addAttribute("list", familyList);

        for (FamilyDto familyDto : familyList) {
            log.info("{}",familyDto.getFamilyRelationshipCode());
            log.info("{}",familyDto.getName());
            log.info("{}",familyDto.getBirthDate());
            log.info("{}",familyDto.getResidentRegistrationNumber());
            log.info("{}",familyDto.getGenderCode());
        }


        return "family";
    }
}
