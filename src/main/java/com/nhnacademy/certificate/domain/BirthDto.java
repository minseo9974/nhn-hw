package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BirthDto {
    private String name;
    private String genderCode;
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;

    public BirthDto(String name, String genderCode, LocalDateTime birthDate, String birthPlaceCode,
                    String registrationBaseAddress) {
        this.name = name;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
    }

    public String getName() {
        return name;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public String getBirthPlaceCode() {
        return birthPlaceCode;
    }

    public String getRegistrationBaseAddress() {
        return registrationBaseAddress;
    }

    public String getBirthDate() {
        LocalDateTime dateTime = this.birthDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분");
        String formattedString = dateTime.format(formatter);
        return formattedString;
    }

}
