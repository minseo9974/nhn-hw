package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.DeathDto;
import com.nhnacademy.certificate.domain.DeathReportDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.service.BirthDeathReportResidentService;
import com.nhnacademy.certificate.service.CertificateIssueService;
import com.nhnacademy.certificate.service.ResidentService;
import com.nhnacademy.certificate.util.GenerateRandom16Digits;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/death")
public class DeathController {
    private final ResidentService residentService;
    private final CertificateIssueService certificateIssueService;
    private final BirthDeathReportResidentService birthDeathReportResidentService;

    public DeathController(ResidentService residentService, CertificateIssueService certificateIssueService,
                           BirthDeathReportResidentService birthDeathReportResidentService) {
        this.residentService = residentService;
        this.certificateIssueService = certificateIssueService;
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @GetMapping("/view/{residentSerialNumber}")
    public String death(@PathVariable Integer residentSerialNumber, Model model) {
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Seoul"));
        long confirmNumber = new GenerateRandom16Digits().getNumber();

        // 증명서 발급 기록 등록 로직
        Resident resident = residentService.getResidentById(residentSerialNumber);
        if (Objects.isNull(resident)) {
            throw new ResidentNotFoundException();
        }
        certificateIssueService.saveCertificate(
                new CertificateIssue(confirmNumber, resident, "출생신고서", date));


        DeathDto deathDto = residentService.getDeathDto(residentSerialNumber);
        DeathReportDto deathReportDto = birthDeathReportResidentService.getDeathReportDto(residentSerialNumber);
        if (Objects.isNull(deathDto.getDeathDate())) {
            throw new RuntimeException("사망자의 사망 관련 정보가 없습니다.");
        }

        log.info("{}",deathDto.getName());
        log.info("{}",deathDto.getResidentRegistrationNumber());
        log.info("{}",deathDto.getDeathDate());
        log.info("{}",deathDto.getDeathPlaceCode());

        log.info("{}", deathReportDto.getName());

        model.addAttribute("death", deathDto);
        model.addAttribute("report", deathReportDto);

        return "death";
    }
}
