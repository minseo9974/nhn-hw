package com.nhnacademy.certificate.service.impl;

import com.nhnacademy.certificate.domain.DeathReportDto;
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
}
