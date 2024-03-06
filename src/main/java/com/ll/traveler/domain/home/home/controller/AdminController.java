package com.ll.traveler.domain.home.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminController {

    @GetMapping("/adm")
    public String adm() {
        return "domain/home/home/adm";
    }
}

