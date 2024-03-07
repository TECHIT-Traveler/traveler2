package com.ll.traveler.domain.member.member.controller;

import com.ll.traveler.domain.member.member.dto.MemberDTO;
import com.ll.traveler.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@ResponseBody
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public String joinProcess(MemberDTO memberDTO) {
        System.out.println(memberDTO.getUsername());
        memberService.joinProcess(memberDTO);

        return "ok";
    }

    @GetMapping("/")
    public String index() {
        return "test";
    }
}
