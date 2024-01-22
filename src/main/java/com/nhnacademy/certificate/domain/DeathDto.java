package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DeathDto {
    private String name;
    private String residentRegistrationNumber;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;

    public DeathDto(String name, String residentRegistrationNumber, LocalDateTime deathDate, String deathPlaceCode,
                    String deathPlaceAddress) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        if (Objects.isNull(deathDate) || Objects.isNull(deathPlaceCode) || Objects.isNull(deathPlaceAddress)) {
            this.deathDate = LocalDateTime.now();
            this.deathPlaceCode = "";
            this.deathPlaceAddress = "";
        } else {
            this.deathDate = deathDate;
            this.deathPlaceCode = deathPlaceCode;
            this.deathPlaceAddress = deathPlaceAddress;
        }
    }

    public String getName() {
        return name;
    }

    public String getResidentRegistrationNumber() {
        String register = this.residentRegistrationNumber;
        register = register.substring(0, register.length() - 7) + "*******";
        return register;
    }

    public String getDeathPlaceCode() {
        return deathPlaceCode;
    }

    public String getDeathPlaceAddress() {
        return deathPlaceAddress;
    }

    public String getDeathDate() {
        LocalDateTime dateTime = this.deathDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분");
        String formattedString = dateTime.format(formatter);
        return formattedString;
    }
}
