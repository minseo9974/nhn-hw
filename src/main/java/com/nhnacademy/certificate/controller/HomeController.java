package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.IndexResidentDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.service.ResidentService;
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

@Slf4j
@Controller
public class HomeController {
    private final ResidentService residentService;

    public HomeController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/")
    public String residentListPage(Model model, @PageableDefault(page = 0,size = 3, sort = "residentSerialNumber",direction = Sort.Direction.ASC)
                                   Pageable pageable) {
        List<IndexResidentDto> list = residentService.getList();

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        int start = (int)pageRequest.getOffset();
        int end = Math.min((start+ pageRequest.getPageSize()),list.size());
        Page<IndexResidentDto> pageList = new PageImpl<>(list.subList(start, end), pageRequest, list.size());

        int pageNumber = pageList.getPageable().getPageNumber();
        int totalPages = pageList.getTotalPages();

        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;


        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("list", pageList);
        return "index";
    }
}
