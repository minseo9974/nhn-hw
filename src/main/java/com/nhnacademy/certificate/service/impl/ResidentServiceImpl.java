package com.nhnacademy.certificate.service.impl;

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
import com.nhnacademy.certificate.repository.ResidentRepository;
import com.nhnacademy.certificate.service.ResidentService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public Page<ResidentDto> getPageList(Pageable pageable) {
        return residentRepository.getAllBy(pageable);
    }

    @Override
    public List<IndexResidentDto> getList() {
        return residentRepository.getList();
    }

    @Override
    public Resident getResidentById(Integer residentSerialNumber) {
        return residentRepository.findById(residentSerialNumber).orElse(null);
    }

    @Override
    public List<IdCardDto> getIdCardList(Integer householdSerialNumber) {
        return residentRepository.findByPkWithHousehold(householdSerialNumber);
    }

    @Override
    public List<FamilyDto> getFamilyDto(Integer residentSerialNumber) {
        return residentRepository.getFamilyDto(residentSerialNumber);
    }

    @Override
    public ResidentOwnerDto getOwner(Integer residentSerialNumber) {
        return residentRepository.findByResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public BirthDto getBirthDto(Integer residentSerialNumber) {
        return residentRepository.findBirthByResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public List<BirthParentDto> getBirthParentDto(Integer residentSerialNumber) {
        return residentRepository.getBirthParentDto(residentSerialNumber);
    }

    @Override
    public BirthReportDto getBirthReportDto(Integer residentSerialNumber) {
        return residentRepository.getBirthReportDto(residentSerialNumber);
    }

    @Override
    public DeathDto getDeathDto(Integer residentSerialNumber) {
        return residentRepository.findDeathByResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public void deleteResidentAndSoOn(Integer residentSerialNumber) {
        residentRepository.deleteById(residentSerialNumber);
        residentRepository.deleteBirthDeathReportByResidentSerialNumber(residentSerialNumber);
        residentRepository.deleteHouseholdCompositionByResidentSerialNumber(residentSerialNumber);
        residentRepository.deleteCertificateIssueByResidentSerialNumber(residentSerialNumber);
        residentRepository.deleteFamilyRelationshipsByResidentSerialNumber(residentSerialNumber);
    }


}
