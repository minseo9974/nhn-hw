package com.nhnacademy.certificate.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class IdCardDto {
    private String householdRelationshipCode;
    private String name;
    private String residentRegistrationNumber;
    private LocalDate reportDate;
    private String householdCompositionChangeReasonCode;

    public String getHouseholdRelationshipCode() {
        return householdRelationshipCode;
    }

    public String getName() {
        return name;
    }

    public String getResidentRegistrationNumber() {
        String register = this.residentRegistrationNumber;
        register = register.substring(0, register.length() - 7) + "*******";
        return register;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public String getHouseholdCompositionChangeReasonCode() {
        return householdCompositionChangeReasonCode;
    }
}
