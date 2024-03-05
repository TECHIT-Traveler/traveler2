package com.ll.traveler.domain.member.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
public class MemberDTO {

    private String username;
    private String password;
    private String email;
    private String nickname;
    private String role;
    private String verificationCode;
}
