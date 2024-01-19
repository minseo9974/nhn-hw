package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.domain.IndexResidentDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {
    Page<ResidentDto> getPageList(Pageable pageable);

    List<IndexResidentDto> getList();
}
