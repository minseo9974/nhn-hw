package com.nhnacademy.certificate.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "certificate_issue")
@Getter
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number", nullable = false)
    private Long certificateConfirmationNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "certificate_type_code", length = 20, nullable = false)
    private String certificateTypeCode;

    @Column(name = "certificate_issue_date", nullable = false)
    private LocalDateTime certificateIssueDate;
}
