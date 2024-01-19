package com.nhnacademy.certificate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/idCard")
public class IdCardController {

    @GetMapping("/view/{householdSerialNumber}")
    public String idCard(@PathVariable Integer householdSerialNumber, Model model) {

        return "idCard";
    }
}
