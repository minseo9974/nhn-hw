package com.nhnacademy.certificate.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IndexResidentDto {
    private Integer residentSerialNumber;
    private String name;
    private Integer householdSerialNumber;
    private Long certificateConfirmationNumber;
    private List<BirthDeathDto> birthDeathDto = new ArrayList<>();
}