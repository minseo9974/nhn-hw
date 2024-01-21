package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BirthReportDto {
    private String name;
    private String residentRegistrationNumber;
    private String birthReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
    private LocalDateTime birthDeathReportDate;

    public BirthReportDto() {
    }

    public BirthReportDto(String name, String residentRegistrationNumber, String birthReportQualificationsCode,
                          String emailAddress, String phoneNumber, LocalDateTime birthDeathReportDate) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.birthReportQualificationsCode = birthReportQualificationsCode;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.birthDeathReportDate = birthDeathReportDate;
    }

    public String getBirthDeathReportDate() {
        LocalDateTime dateTime = this.birthDeathReportDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
        String formattedString = dateTime.format(formatter);
        return formattedString;
    }

    public String getResidentRegistrationNumber() {
        String register = this.residentRegistrationNumber;
        register = register.substring(0, register.length() - 7) + "*******";
        return register;
    }

    public String getName() {
        return name;
    }

    public String getBirthReportQualificationsCode() {
        return birthReportQualificationsCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
