package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.CertificateDto;
import com.nhnacademy.certificate.domain.IndexResidentDto;
import com.nhnacademy.certificate.service.CertificateIssueService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/certificate")
public class CertificateController {
    private final CertificateIssueService certificateIssueService;

    public CertificateController(CertificateIssueService certificateIssueService) {
        this.certificateIssueService = certificateIssueService;
    }

    @GetMapping("/view/{residentSerialNumber}")
    public String certificate(@PathVariable Integer residentSerialNumber,
                              @PageableDefault(page = 0,size = 3,sort = "certificateIssueDate",direction = Sort.Direction.ASC)
                              Pageable pageable, Model model) {
        List<CertificateDto> list = certificateIssueService.getCertificateList(residentSerialNumber);
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        int start = (int)pageRequest.getOffset();
        int end = Math.min((start+ pageRequest.getPageSize()),list.size());
        Page<CertificateDto> pageList = new PageImpl<>(list.subList(start, end), pageRequest, list.size());

        int pageNumber = pageList.getPageable().getPageNumber();
        int totalPages = pageList.getTotalPages();

        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;


        model.addAttribute("serialCode", residentSerialNumber);
        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("list", pageList);

        model.addAttribute("list", pageList);

        return "certificate";
    }
}
