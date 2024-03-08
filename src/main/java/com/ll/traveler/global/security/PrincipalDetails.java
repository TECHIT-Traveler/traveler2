package com.ll.traveler.global.security;

import com.ll.traveler.domain.member.member.entity.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//시큐리티가 /login 주소요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료가되면 시큐리티 session 을 만든다.
//오브젝트 타입 => Authentication 타입 객체
// Authentication 안에 Member 정보가 있어야 됨.
//Authentication 객체에 저장할 수 있는 유일한 타입

@Data
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member;
    private Map<String, Object> attributes;
    private static final long serialVersionUID = 1L;
    // Java 직렬화에서 사용되는 필드입니다. 이 필드는 직렬화된 객체의 버전을 식별하기 위해 사용됩니다.

    // 일반 시큐리티 로그인시 사용
    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // OAuth2.0 로그인시 사용
    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
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

    // 리소스 서버로 부터 받는 회원정보
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // Member의 PrimaryKey
    @Override
    public String getName() {
        return member.getId()+"";
    }
}
