package com.nhnacademy.certificate.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
public class BirthParentDto {
    private String name;
    private String residentRegistrationNumber;
    private String familyRelationshipCode;

    public BirthParentDto(String name, String residentRegistrationNumber, String familyRelationshipCode) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.familyRelationshipCode = familyRelationshipCode;
    }

    public String getFamilyRelationshipCode() {
        return familyRelationshipCode;
    }

    public String getResidentRegistrationNumber() {
        String register = this.residentRegistrationNumber;
        register = register.substring(0, register.length() - 7) + "*******";
        return register;
    }

    public String getName() {
        return name;
    }

}
