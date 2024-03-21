package com.ll.traveler.domain.member.member.controller;


import com.ll.traveler.domain.member.member.dto.MemberDTO;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.service.MemberService;
import com.ll.traveler.global.security.PrincipalDetails;
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

    @GetMapping("/login")
    public String login() {
        return "domain/member/member/login";
    }

    @GetMapping("/checkUsername/{username}")
    @ResponseBody
    public ResponseEntity<?> checkUsernameAvailability(@PathVariable("username") String username) {
        boolean isAvailable = memberService.isUsernameAvailable(username);
        return ResponseEntity.ok().body(isAvailable);
    }

    @GetMapping("/checkEmail/{email}")
    @ResponseBody
    public ResponseEntity<?> checkEmailDuplication(@PathVariable("email") String email) {
        Member member = memberService.findByEmail(email);
        if (member != null) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @GetMapping("/login-info")
    @ResponseBody
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();

        Member member = memberService.findByUsername(username);

        PrincipalDetails principalDetails = new PrincipalDetails(member);
        return ResponseEntity.ok(principalDetails.getNickname());
    }

    @PostMapping("/logout")
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
}
