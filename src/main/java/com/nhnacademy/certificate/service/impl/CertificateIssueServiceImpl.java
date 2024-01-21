package com.nhnacademy.certificate.service.impl;

import com.nhnacademy.certificate.domain.CertificateDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.repository.CertificateIssueRepository;
import com.nhnacademy.certificate.service.CertificateIssueService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CertificateIssueServiceImpl implements CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;

    public CertificateIssueServiceImpl(CertificateIssueRepository certificateIssueRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
    }

    @Transactional
    @Override
    public void saveCertificate(CertificateIssue certificateIssue) {
        certificateIssueRepository.save(certificateIssue);
    }

    @Override
    public List<CertificateDto> getCertificateList(Integer residentSerialNumber) {
        return certificateIssueRepository.findByResident_ResidentSerialNumber(residentSerialNumber);
    }
}
