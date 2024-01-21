package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.BirthDto;
import com.nhnacademy.certificate.domain.BirthParentDto;
import com.nhnacademy.certificate.domain.BirthReportDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.service.CertificateIssueService;
import com.nhnacademy.certificate.service.ResidentService;
import com.nhnacademy.certificate.util.GenerateRandom16Digits;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/birth")
public class BirthController {
    private final CertificateIssueService certificateIssueService;
    private final ResidentService residentService;

    public BirthController(CertificateIssueService certificateIssueService, ResidentService residentService) {
        this.certificateIssueService = certificateIssueService;
        this.residentService = residentService;
    }

    @GetMapping("/view/{residentSerialNumber}")
    public String birth(@PathVariable Integer residentSerialNumber, Model model) {
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Seoul"));
        long confirmNumber = new GenerateRandom16Digits().getNumber();

        // 증명서 발급 기록 등록 로직
        Resident resident = residentService.getResidentById(residentSerialNumber);
        if (Objects.isNull(resident)) {
            throw new ResidentNotFoundException();
        }
        certificateIssueService.saveCertificate(
                new CertificateIssue(confirmNumber, resident, "사망신고서", date));

        BirthDto birthDto = residentService.getBirthDto(residentSerialNumber);
        List<BirthParentDto> birthParentDto = residentService.getBirthParentDto(residentSerialNumber);
        BirthReportDto birthReportDto = residentService.getBirthReportDto(residentSerialNumber);
        log.debug("size -> {}", birthParentDto.size());
        for (BirthParentDto parentDto : birthParentDto) {
            log.info("name -> {}", parentDto.getName());
            log.info("num -> {}", parentDto.getResidentRegistrationNumber());
            log.info("num -> {}", parentDto.getFamilyRelationshipCode());
        }

        log.info("{}", birthReportDto.getName());
        log.info("{}", birthReportDto.getResidentRegistrationNumber());
        log.info("{}", birthReportDto.getBirthReportQualificationsCode());
        log.info("{}", birthReportDto.getEmailAddress());
        log.info("{}", birthReportDto.getPhoneNumber());

        BirthParentDto father = null;
        BirthParentDto mother = null;
        for (BirthParentDto parentDto : birthParentDto) {
            if (parentDto.getFamilyRelationshipCode().equals("부")) {
                father = parentDto;
            }
            if (parentDto.getFamilyRelationshipCode().equals("모")) {
                mother = parentDto;
            }
        }

        model.addAttribute("birth", birthDto);
        model.addAttribute("father", father);
        model.addAttribute("mother", mother);
        model.addAttribute("report", birthReportDto);


        return "birth";
    }

}
