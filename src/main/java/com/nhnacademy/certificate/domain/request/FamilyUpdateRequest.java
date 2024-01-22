package com.nhnacademy.certificate.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FamilyUpdateRequest {
    @NotNull(message = "relationShip is empty")
    @NotBlank(message = "relationShip is empty")
    private String relationShip;
}
