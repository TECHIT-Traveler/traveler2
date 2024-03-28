package com.ll.traveler.domain.member.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoDTO {
    private long id;
    private String username;
    private String nickname;
    private String email;
    private String role;
    private String provider;
    private String gyeonggiSave;
}
