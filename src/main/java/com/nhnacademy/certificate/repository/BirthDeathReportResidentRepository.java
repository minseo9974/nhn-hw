package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.DeathReportDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BirthDeathReportResidentRepository
        extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {

    @Query(
            "select new com.nhnacademy.certificate.domain.DeathReportDto(r.name,r.residentRegistrationNumber,b.deathReportQualificationsCode,b.emailAddress,b.phoneNumber,b.birthDeathReportDate) " +
                    "from Resident r left join BirthDeathReportResident b on r.residentSerialNumber = b.reportResidentSerialNumber " +
                    "where b.pk.residentSerialNumber = :residentSerialNumber and b.pk.birthDeathTypeCode = '사망' "
    )
    DeathReportDto getDeathReport(@Param("residentSerialNumber") Integer residentSerialNumber);
}