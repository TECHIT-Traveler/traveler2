package com.ll.traveler.domain.member.member.controller;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ApiV1Controller {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("main")
    public String main(){
        return "<h1>home</h1>";
    }

    @PostMapping("join")
    public String join(@RequestBody Member member){
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRole("ROLE_MEMBER");
        memberRepository.save(member);
        return "회원가입";
    }

}
