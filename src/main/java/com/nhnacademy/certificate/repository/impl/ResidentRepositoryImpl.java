package com.nhnacademy.certificate.repository.impl;


import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.nhnacademy.certificate.domain.BirthDeathDto;
import com.nhnacademy.certificate.domain.BirthParentDto;
import com.nhnacademy.certificate.domain.BirthReportDto;
import com.nhnacademy.certificate.domain.IndexResidentDto;
import com.nhnacademy.certificate.entity.QBirthDeathReportResident;
import com.nhnacademy.certificate.entity.QCertificateIssue;
import com.nhnacademy.certificate.entity.QFamilyRelationship;
import com.nhnacademy.certificate.entity.QHouseholdCompositionResident;
import com.nhnacademy.certificate.entity.QResident;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.custom.ResidentCustom;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
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

    @Override
    public List<BirthParentDto> getBirthParentDto(Integer residentSerialNumber) {
        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        String father = "부";
        String mother = "모";


        List<Tuple> list = from(familyRelationship)
                .leftJoin(resident)
                .on(resident.residentSerialNumber.eq(familyRelationship.pk.familyResidentSerialNumber))
                .where(familyRelationship.familyRelationshipCode.eq(father)
                        .or(familyRelationship.familyRelationshipCode.eq(mother))
                        .and(familyRelationship.pk.baseResidentSerialNumber.eq(residentSerialNumber)))
                .select(resident.name, resident.residentRegistrationNumber, familyRelationship.familyRelationshipCode)
                .fetch();
        log.debug("size -> {}", list.size());
        return list.stream()
                .map((a) -> new BirthParentDto(a.get(resident.name), a.get(resident.residentRegistrationNumber),
                        a.get(familyRelationship.familyRelationshipCode)))
                .collect(
                        Collectors.toList());
    }

    @Override
    public BirthReportDto getBirthReportDto(Integer residentSerialNumber) {
        QResident resident = QResident.resident;
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;

        List<Tuple> dto = from(birthDeathReportResident)
                .leftJoin(resident)
                .on(resident.residentSerialNumber.eq(birthDeathReportResident.reportResidentSerialNumber))
                .where(birthDeathReportResident.pk.residentSerialNumber.eq(residentSerialNumber),
                        birthDeathReportResident.pk.birthDeathTypeCode.eq("출생"))
                .select(resident.name, resident.residentRegistrationNumber,
                        birthDeathReportResident.birthReportQualificationsCode,
                        birthDeathReportResident.emailAddress, birthDeathReportResident.phoneNumber,birthDeathReportResident.birthDeathReportDate)
                .fetch();
        return dto.stream()
                .map((a) -> new BirthReportDto(a.get(resident.name), a.get(resident.residentRegistrationNumber),
                        a.get(birthDeathReportResident.birthReportQualificationsCode),
                        a.get(birthDeathReportResident.emailAddress),
                        a.get(birthDeathReportResident.phoneNumber),
                        a.get(birthDeathReportResident.birthDeathReportDate))).collect(Collectors.toList()).get(0);
    }

}