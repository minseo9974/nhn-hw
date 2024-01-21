package com.nhnacademy.certificate.domain;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FamilyDto {
    private String familyRelationshipCode;
    private String name;
    private LocalDateTime birthDate;
    private String residentRegistrationNumber;
    private String genderCode;

    public FamilyDto(String familyRelationshipCode, String name, LocalDateTime birthDate,
                     String residentRegistrationNumber, String genderCode) {
        this.familyRelationshipCode = familyRelationshipCode;
        this.name = name;
        this.birthDate = birthDate;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
    }


    public String getFamilyRelationshipCode() {
        return this.familyRelationshipCode;
    }

    public String getName() {
        return this.name;
    }

    public String getBirthDate() {
        LocalDateTime dateTime = this.birthDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
        String formattedString = dateTime.format(formatter);
        return formattedString;
    }

    public String getResidentRegistrationNumber() {
        String register = this.residentRegistrationNumber;
        register = register.substring(0, register.length() - 7) + "*******";
        return register;
    }

    public String getGenderCode() {
        return this.genderCode;
    }

}
