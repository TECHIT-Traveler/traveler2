package com.ll.traveler.domain.member.member.service;


import com.ll.traveler.domain.member.member.dto.MemberDTO;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}


