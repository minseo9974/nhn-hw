package com.nhnacademy.certificate.repository.impl;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.nhnacademy.certificate.domain.BirthDeathDto;
import com.nhnacademy.certificate.domain.IndexResidentDto;
import com.nhnacademy.certificate.entity.QBirthDeathReportResident;
import com.nhnacademy.certificate.entity.QCertificateIssue;
import com.nhnacademy.certificate.entity.QHouseholdCompositionResident;
import com.nhnacademy.certificate.entity.QResident;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.custom.ResidentCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentCustom {
    public ResidentRepositoryImpl() {
        super(Resident.class);
    }

    public List<IndexResidentDto> getList() {
        QResident resident = QResident.resident;
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QHouseholdCompositionResident household = QHouseholdCompositionResident.householdCompositionResident;
        QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;

        return from(resident)
                .leftJoin(resident.certificateIssues, certificateIssue)
                .leftJoin(resident.birthDeathReportResidents, birthDeathReportResident)
                .leftJoin(resident.householdCompositionResidents, household)
                .select(Projections.fields(IndexResidentDto.class,
                                resident.residentSerialNumber,
                                resident.name,
                                household.pk.householdSerialNumber,
                                certificateIssue.certificateConfirmationNumber),
                        Projections.fields(BirthDeathDto.class,
                                birthDeathReportResident.pk.birthDeathTypeCode).as("birthDeathDto")
                )
                .transform(
                        groupBy(resident.residentSerialNumber).list(
                                Projections.fields(
                                        IndexResidentDto.class,
                                        resident.residentSerialNumber,
                                        resident.name,
                                        household.pk.householdSerialNumber,
                                        certificateIssue.certificateConfirmationNumber,
                                        list(
                                                Projections.fields(
                                                        BirthDeathDto.class,
                                                        birthDeathReportResident.pk.birthDeathTypeCode
                                                )
                                        ).as("birthDeathDto")
                                )
                        )
                );
    }
}