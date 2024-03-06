package com.ll.traveler.domain.member.member.entity;

import com.ll.traveler.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@AllArgsConstructor(access = PROTECTED) // 클래스의 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성
@NoArgsConstructor(access = PROTECTED) // 파라미터가 없는 디폴트 생성자를 자동으로 생성
@Getter
@Setter
@ToString(callSuper = true)// 클래스의 toString() 메서드를 생성할 때 부모 클래스의 필드들도 포함할지 여부를 지정
public class Member extends BaseEntity {
    private int adminLevel = 0;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickname;

    private String providerTypeCode;
    private String verificationCode;
    private String role;

}

