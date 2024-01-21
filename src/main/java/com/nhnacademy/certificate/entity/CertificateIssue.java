package com.nhnacademy.certificate.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "certificate_issue")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number", nullable = false)
    private Long certificateConfirmationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "certificate_type_code", length = 20, nullable = false)
    private String certificateTypeCode;

    @Column(name = "certificate_issue_date", nullable = false)
    private LocalDate certificateIssueDate;
}
