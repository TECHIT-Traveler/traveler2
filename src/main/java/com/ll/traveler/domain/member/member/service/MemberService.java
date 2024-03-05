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
    public void joinProcess(MemberDTO memberDTO) {

        Boolean isExist = memberRepository.existsByUsername(memberDTO.getUsername());

        if (isExist) {

            return;
        }
        Member member = Member.builder()
                .username(memberDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(memberDTO.getPassword()))
                .email(memberDTO.getEmail())
                .nickname(memberDTO.getNickname())
                .verificationCode(memberDTO.getVerificationCode())
                .role("ROLE_ADMIN")
                .build();

        memberRepository.save(member);
    }
}
