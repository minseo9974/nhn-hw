package com.nhnacademy.certificate.domain;

import com.nhnacademy.certificate.util.GenerateRandom16Digits;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CertificateDto {
    private Long certificateConfirmationNumber;
    private String certificateTypeCode;
    private LocalDate certificateIssueDate;

    public CertificateDto(Long certificateConfirmationNumber, String certificateTypeCode,
                          LocalDate certificateIssueDate) {
        this.certificateConfirmationNumber = certificateConfirmationNumber;
        this.certificateTypeCode = certificateTypeCode;
        this.certificateIssueDate = certificateIssueDate;
    }

    public String getCertificateConfirmationNumber() {
        String result = String.valueOf(this.certificateConfirmationNumber);
        String first = result.substring(0, 8);
        String second = result.substring(8, 16);

        String resultString = first + "-" + second;

        return resultString;
    }

    public String getCertificateTypeCode() {
        return certificateTypeCode;
    }

    public String getCertificateIssueDate() {
        LocalDate dateTime = this.certificateIssueDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
        String formattedString = dateTime.format(formatter);
        return formattedString;
    }
}
