package com.ll.traveler.domain.place.place.gangwon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class GangwonController {

    private final GangwonService gangwonService;

    @GetMapping("/gangwon")
    public String showGangwon(Model model) {
        List<Gangwon> gangwonList = gangwonService.getAllGangwonData();
        model.addAttribute("gangwonList", gangwonList);
        return "domain/place/place/gangwon";
    }
}
