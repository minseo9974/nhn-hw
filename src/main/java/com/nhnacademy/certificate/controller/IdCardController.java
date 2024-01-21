package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.AddressDto;
import com.nhnacademy.certificate.domain.HouseholdDto;
import com.nhnacademy.certificate.domain.IdCardDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.service.CertificateIssueService;
import com.nhnacademy.certificate.service.HouseholdMovementAddressService;
import com.nhnacademy.certificate.service.HouseholdService;
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
@RequestMapping("/idCard")
public class IdCardController {
    private final CertificateIssueService certificateIssueService;
    private final ResidentService residentService;
    private final HouseholdMovementAddressService householdMovementAddressService;
    private final HouseholdService householdService;

    public IdCardController(CertificateIssueService certificateIssueService, ResidentService residentService,
                            HouseholdMovementAddressService householdMovementAddressService,
                            HouseholdService householdService) {
        this.certificateIssueService = certificateIssueService;
        this.residentService = residentService;
        this.householdMovementAddressService = householdMovementAddressService;
        this.householdService = householdService;
    }

    @GetMapping("/view/{householdSerialNumber}/{residentSerialNumber}")
    public String idCard(@PathVariable Integer householdSerialNumber, @PathVariable Integer residentSerialNumber,
                         Model model) {
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
                new CertificateIssue(confirmNumber, resident, "주민등록등본", date));

        // 주민등록본 세대원 정보
        List<IdCardDto> residentList = residentService.getIdCardList(householdSerialNumber);
        model.addAttribute("residents", residentList);
        for (IdCardDto idCardDto : residentList) {
            log.info("{}", idCardDto.getHouseholdRelationshipCode());
            log.info("{}", idCardDto.getName());
            log.info("{}", idCardDto.getResidentRegistrationNumber());
            log.info("{}", idCardDto.getReportDate());
            log.info("{}", idCardDto.getHouseholdCompositionChangeReasonCode());
        }

        //주소 정보
        List<AddressDto> addressList = householdMovementAddressService.getAddressList(householdSerialNumber);
        model.addAttribute("address", addressList);

        //세대주 정보
        HouseholdDto household = householdService.getHousehold(householdSerialNumber);
        model.addAttribute("household", household);

        return "idCard";
    }
}
