package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeathReportDto {
    private String name;
    private String residentRegistrationNumber;
    private String deathReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
    private LocalDateTime birthDeathReportDate;

    public DeathReportDto(String name, String residentRegistrationNumber, String deathReportQualificationsCode,
                          String emailAddress, String phoneNumber, LocalDateTime birthDeathReportDate) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.deathReportQualificationsCode = deathReportQualificationsCode;
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

    public String getName() {
        return name;
    }

    public String getDeathReportQualificationsCode() {
        return deathReportQualificationsCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getResidentRegistrationNumber() {
        String register = this.residentRegistrationNumber;
        register = register.substring(0, register.length() - 7) + "*******";
        return register;
    }
}
