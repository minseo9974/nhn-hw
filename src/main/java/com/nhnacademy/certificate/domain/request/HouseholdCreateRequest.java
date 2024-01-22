package com.nhnacademy.certificate.domain.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class HouseholdCreateRequest {
    private Integer householdSerialNumber;
    private Integer householdResidentSerialNumber;
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
}
