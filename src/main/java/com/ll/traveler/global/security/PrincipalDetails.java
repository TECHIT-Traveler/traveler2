package com.ll.traveler.global.security;

import com.ll.traveler.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//시큐리티가 /login 주소요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료가되면 시큐리티 session 을 만든다.
//오브젝트 타입 => Authentication 타입 객체
// Authentication 안에 Member 정보가 있어야 됨.
//Authentication 객체에 저장할 수 있는 유일한 타입

@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private Member member;

    // 일반 시큐리티 로그인시 사용
    public PrincipalDetails(Member member) {
        this.member = member;
    }

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Collection<GrantedAuthority> collect = new ArrayList<>();
       collect.add(new GrantedAuthority() {
           @Override
           public String getAuthority() {
               return member.getRole();
           }
       });
       return collect;
   }

   @Override
    public String getPassword(){
        return member.getPassword();
   }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
