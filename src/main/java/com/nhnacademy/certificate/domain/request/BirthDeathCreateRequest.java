package com.nhnacademy.certificate.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Data;

@Data
public class BirthDeathCreateRequest {
    private Integer birthSerialNum;
    private String birthType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String birthQualification;
    private String email;
    private String phone;
}
