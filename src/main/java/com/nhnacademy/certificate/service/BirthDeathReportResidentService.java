package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.DeathReportDto;

public interface BirthDeathReportResidentService {
    DeathReportDto getDeathReportDto(Integer residentSerialNumber);
}
