package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResidentOwnerDto {
    private String name;
    private LocalDateTime birthDate;
    private String residentRegistrationNumber;
    private String genderCode;
    private String registrationBaseAddress;

    public ResidentOwnerDto(String name, LocalDateTime birthDate, String residentRegistrationNumber, String genderCode,
                            String registrationBaseAddress) {
        this.name = name;
        this.birthDate = birthDate;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
        this.registrationBaseAddress = registrationBaseAddress;
    }

    public String getRegistrationBaseAddress() {
        return registrationBaseAddress;
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
