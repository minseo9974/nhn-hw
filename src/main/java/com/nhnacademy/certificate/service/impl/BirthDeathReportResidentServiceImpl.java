package com.nhnacademy.certificate.service.impl;

import com.nhnacademy.certificate.domain.DeathReportDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import com.nhnacademy.certificate.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.certificate.service.BirthDeathReportResidentService;
import org.springframework.stereotype.Service;

@Service
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService {
    private BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    public BirthDeathReportResidentServiceImpl(BirthDeathReportResidentRepository birthDeathReportResidentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
    }

    @Override
    public DeathReportDto getDeathReportDto(Integer residentSerialNumber) {
        return birthDeathReportResidentRepository.getDeathReport(residentSerialNumber);
    }

    @Override
    public BirthDeathReportResident getBD(BirthDeathReportResident.Pk pk) {
        return birthDeathReportResidentRepository.findById(pk).orElse(null);
    }

    @Override
    public void saveBD(BirthDeathReportResident birthDeathReportResident) {
        birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    public void deleteBD(BirthDeathReportResident birthDeathReportResident) {
        birthDeathReportResidentRepository.delete(birthDeathReportResident);
    }

    @Override
    public boolean exist(BirthDeathReportResident birthDeathReportResident) {
        return birthDeathReportResidentRepository.existsById(birthDeathReportResident.getPk());
    }

}
