package com.nhnacademy.certificate.repository.custom;

import com.nhnacademy.certificate.domain.BirthParentDto;
import com.nhnacademy.certificate.domain.BirthReportDto;
import com.nhnacademy.certificate.domain.FamilyDto;
import com.nhnacademy.certificate.domain.IndexResidentDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ResidentCustom {
    List<IndexResidentDto> getList();

    List<BirthParentDto> getBirthParentDto(Integer residentSerialNumber);

    BirthReportDto getBirthReportDto(Integer residentSerialNumber);
}
