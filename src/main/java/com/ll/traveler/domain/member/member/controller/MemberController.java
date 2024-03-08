package com.ll.traveler.domain.member.member.controller;


import com.ll.traveler.domain.member.member.dto.MemberDTO;
import com.ll.traveler.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String showJoin(MemberDTO memberDTO) {
        return "domain/member/member/join";
    }

    @PostMapping("/join")
    public String joinProc(MemberDTO memberDto) {

        memberService.joinProc(memberDto);

        return "redirect:/";

    }

    @GetMapping("/login")
    public String login() {
        return "domain/member/member/login";
    }
}
