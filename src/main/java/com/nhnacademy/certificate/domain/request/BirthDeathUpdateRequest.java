package com.nhnacademy.certificate.domain.request;

import lombok.Data;

@Data
public class BirthDeathUpdateRequest {
    private String email;
    private String phone;
}
