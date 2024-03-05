package com.ll.traveler.domain.member.member.service;

import com.ll.traveler.domain.member.member.dto.CustomMemberDetails;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        Member memberData = memberRepository.findByUsername(username);

        if (memberData != null) {

            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomMemberDetails(memberData);
        }

        return null;
    }
}