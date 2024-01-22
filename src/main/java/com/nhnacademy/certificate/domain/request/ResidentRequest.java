package com.nhnacademy.certificate.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ResidentRequest {
    @NotNull(message = "registrationBaseAddress is empty")
    @NotBlank(message = "registrationBaseAddress is empty")
    @Size(min = 1, max = 500, message = "registrationBaseAddress length is wrong")
    private String registrationBaseAddress;


}
