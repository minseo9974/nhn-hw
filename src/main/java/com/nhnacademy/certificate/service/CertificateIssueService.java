package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.CertificateDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface CertificateIssueService {
    void saveCertificate(CertificateIssue certificateIssue);

    List<CertificateDto> getCertificateList(Integer residentSerialNumber);
}
