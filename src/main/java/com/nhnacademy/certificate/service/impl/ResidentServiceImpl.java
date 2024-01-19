package com.nhnacademy.certificate.service.impl;

import com.nhnacademy.certificate.domain.IndexResidentDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.ResidentRepository;
import com.nhnacademy.certificate.service.ResidentService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public Page<ResidentDto> getPageList(Pageable pageable) {
        return residentRepository.getAllBy(pageable);
    }

    @Override
    public List<IndexResidentDto> getList() {
        return residentRepository.getList();
    }

}
