package com.ll.traveler.domain.member.member.service;


import com.ll.traveler.domain.member.member.dto.MemberDTO;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void joinProc(MemberDTO memberDTO) {
        // 비밀번호 암호화
        String encodedPassword = bCryptPasswordEncoder.encode(memberDTO.getPassword());

        // MemberDTO로부터 Member 엔티티 생성
        Member member = Member.builder()
                .username(memberDTO.getUsername())
                .password(encodedPassword)
                .email(memberDTO.getEmail())
                .nickname(memberDTO.getNickname())
                .role(memberDTO.getRole())
                .build();

        memberRepository.save(member);
    }

    public boolean isUsernameAvailable(String username) {
        return !memberRepository.existsByUsername(username);
    }

    public Member findByEmail(String email) {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if(memberOptional.isPresent()) {
            return memberOptional.get();
        }
        return null;
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findById(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(memberOptional.isPresent()) {
            return memberOptional.get();
        }
        return null;
    }
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }
}


