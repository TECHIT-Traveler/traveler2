package com.ll.traveler.domain.place.place.controller;

import com.ll.traveler.domain.place.place.repositiry.PlaceRepository;
import com.ll.traveler.domain.place.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceRepository placeRepository;
    private final PlaceService placeService;

    @GetMapping("/list")
    @ResponseBody
    public String list() {
        return null;
    }

    @PostMapping("/list")
    @ResponseBody
    public String showlist(){
        return null;
    }

    @GetMapping("/detail")
    @ResponseBody
    public String detail() {
        return null;
    }
}
