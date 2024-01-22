package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.DeathReportDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;

public interface BirthDeathReportResidentService {
    DeathReportDto getDeathReportDto(Integer residentSerialNumber);

    BirthDeathReportResident getBD(BirthDeathReportResident.Pk pk);

    void saveBD(BirthDeathReportResident birthDeathReportResident);

    void deleteBD(BirthDeathReportResident birthDeathReportResident);

    boolean exist(BirthDeathReportResident birthDeathReportResident);
}
