package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.CertificateDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    List<CertificateDto> findByResident_ResidentSerialNumber(Integer residentSerialNumber);
}