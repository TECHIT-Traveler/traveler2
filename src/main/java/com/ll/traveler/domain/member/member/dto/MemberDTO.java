package com.ll.traveler.domain.member.member.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private String username;

//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z)(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
   // private String passwordConfirm;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private String nickname;

    private String role;

}
