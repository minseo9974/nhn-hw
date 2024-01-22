package com.nhnacademy.certificate.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Data;

@Data
public class HouseholdMovementCreateRequest {
    private Integer reportDate;
    private String movementAdd;
    private String lastAddress;
}
