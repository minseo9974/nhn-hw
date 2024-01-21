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
public class AddressDto {
    private String lastAddressYN;
    private String houseMovementAddress;
    private LocalDate houseMovementReportDate;
}
