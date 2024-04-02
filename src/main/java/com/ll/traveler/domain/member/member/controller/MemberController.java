package com.ll.traveler.domain.member.member.controller;


import com.ll.traveler.domain.member.member.dto.MemberDTO;
import com.ll.traveler.domain.member.member.dto.MemberInfoDTO;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.service.MemberService;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.place.place.service.PlaceService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final PlaceService placeService;
    @GetMapping("/member/join")
    public String showJoin(MemberDTO memberDTO) {
        return "domain/member/member/join";
    }

    @PostMapping("/member/join")
    @ResponseBody
    public ResponseEntity<?> joinProc(@RequestBody @Valid MemberDTO memberDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 유효하지 않습니다.");
        }

        if (!memberDto.getPassword().equals(memberDto.getConfirmPassword())){
            return ResponseEntity.badRequest().body("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        memberService.joinProc(memberDto);
        return ResponseEntity.ok("회원가입 완료");

    }

    @GetMapping("/member/login")
    public String login() {
        return "domain/member/member/login";
    }

    @GetMapping("/member/checkUsername/{username}")
    @ResponseBody
    public ResponseEntity<?> checkUsernameAvailability(@PathVariable("username") String username) {
        boolean isAvailable = memberService.isUsernameAvailable(username);
        return ResponseEntity.ok().body(isAvailable);
    }

    @GetMapping("/member/checkEmail/{email}")
    @ResponseBody
    public ResponseEntity<?> checkEmailDuplication(@PathVariable("email") String email) {
        Member member = memberService.findByEmail(email);
        if (member != null) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @GetMapping("/member/login-info")
    @ResponseBody
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();

        Member member = memberService.findByUsername(username);

        MemberInfoDTO memberInfo = new MemberInfoDTO();
        memberInfo.setId(member.getId());
        memberInfo.setUsername(member.getUsername());
        memberInfo.setNickname(member.getNickname());
        memberInfo.setEmail(member.getEmail());
        memberInfo.setRole(member.getRole());
        memberInfo.setProvider(member.getProvider());

        return ResponseEntity.ok(memberInfo);
    }

    @PostMapping("/member/logout")
    @ResponseBody
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("로그아웃");
        //세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 쿠키 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
            }
        }

        return ResponseEntity.ok("로그아웃 성공");
    }

    @GetMapping("/api/v1/members/{id}/places")
    public ResponseEntity<?> getLikedPlaces(@PathVariable("id") long id) {
        List<Place> likedPlaces = placeService.getLikedPlacesByMemberId(id);

        return ResponseEntity.ok().body(likedPlaces);
    }
}
