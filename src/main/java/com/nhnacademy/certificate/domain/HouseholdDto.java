package com.nhnacademy.certificate.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HouseholdDto {
    private String name;
    private String householdCompositionReasonCode;
    private LocalDate householdCompositionDate;
}
