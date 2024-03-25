package com.ll.traveler.global.email;

import com.ll.traveler.global.email.DTO.EmailCheckDTO;
import com.ll.traveler.global.email.DTO.EmailRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailSendService mailService;

    @PostMapping ("/mailSend")
    public String mailSend(@RequestBody @Valid EmailRequestDTO emailDTO){
        System.out.println("이메일 인증 요청이 들어옴");
        System.out.println("이메일 인증 이메일 :" + emailDTO.getEmail());
        return mailService.joinEmail(emailDTO.getEmail());
    }

    @PostMapping("/mailauthCheck")
    public String AuthCheck(@RequestBody @Valid EmailCheckDTO emailCheckDTO){
        Boolean Checked=mailService.CheckAuthNum(emailCheckDTO.getEmail(), emailCheckDTO.getAuthNum());
        if(Checked){
            return "ok";
        }
        else{
            throw new NullPointerException("다시 시도해 주세요!");
        }
    }

}
