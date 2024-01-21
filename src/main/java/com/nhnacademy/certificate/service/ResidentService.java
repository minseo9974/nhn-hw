package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.BirthDto;
import com.nhnacademy.certificate.domain.BirthParentDto;
import com.nhnacademy.certificate.domain.BirthReportDto;
import com.nhnacademy.certificate.domain.DeathDto;
import com.nhnacademy.certificate.domain.FamilyDto;
import com.nhnacademy.certificate.domain.IdCardDto;
import com.nhnacademy.certificate.domain.IndexResidentDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.domain.ResidentOwnerDto;
import com.nhnacademy.certificate.entity.Resident;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {
    Page<ResidentDto> getPageList(Pageable pageable);

    List<IndexResidentDto> getList();

    Resident getResidentById(Integer residentSerialNumber);

    List<IdCardDto> getIdCardList(Integer householdSerialNumber);

    List<FamilyDto> getFamilyDto(Integer residentSerialNumber);

    ResidentOwnerDto getOwner(Integer residentSerialNumber);

    BirthDto getBirthDto(Integer residentSerialNumber);

    List<BirthParentDto> getBirthParentDto(Integer residentSerialNumber);

    BirthReportDto getBirthReportDto(Integer residentSerialNumber);

    DeathDto getDeathDto(Integer residentSerialNumber);

    void deleteResidentAndSoOn(Integer residentSerialNumber);

}
