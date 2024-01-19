package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.custom.ResidentCustom;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> , ResidentCustom {
    Page<ResidentDto> getAllBy(Pageable pageable);
}