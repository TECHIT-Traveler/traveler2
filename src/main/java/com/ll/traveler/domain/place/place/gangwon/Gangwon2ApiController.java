package com.ll.traveler.domain.place.place.gangwon;


import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Gangwon2ApiController {

    private final Gangwon2ApiService gangwon2ApiService;
    private final MemberService memberService;

    @GetMapping("/apiGangwon2")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = "https://api.odcloud.kr/api/15096638/v1/uddi:718fc83f-ef5d-41b0-9290-afbe366ad802?" +
                "page=1" +
                "&perPage=10" +
                "&serviceKey=CXFCnn25be0lhjZXJtmWuH4WDFCB7Pasr%2Bci5Gz8OV3w9jTtGldsTpX%2BqEL7KF9pLgeXornjNYMWDA%2BHhXp4xQ%3D%3D";


        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine).append("\n");
            }
        } finally {
            con.disconnect();
        }

        log.info("data = {}", result.toString());

        gangwon2ApiService.mapJsonToGyeonggi2List(result.toString());

        return result.toString();
    }

    @GetMapping("/gangwon2")
    public List<Gangwon2> showGangwon2() {
        List<Gangwon2> gangwon2List = gangwon2ApiService.getAllGangwon2Data();
        return gangwon2List;
    }

    @GetMapping("/gangwon2/{id}")
    public Gangwon2 detail(@PathVariable("id") Long id) {
        Gangwon2 detail = gangwon2ApiService.getGangwon2DataById(id);
        return detail;
    }
    @GetMapping("/gangwon2/search")
    public List<Gangwon2> searchAllGangwon() {
        return gangwon2ApiService.getAllGangwon2Data();
    }

    @GetMapping("/gangwon2/checkLike/{id}")
    public boolean checkLikeStatus(@PathVariable("id") Long id, Authentication authentication) {
        Gangwon2 gangwon2 = gangwon2ApiService.getGangwon2DataById(id);
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Member member = memberService.findByUsername(username);

        if (gangwon2.hasLike(member)) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/gangwon2/like/{id}")
    public void like(@PathVariable("id") Long id,Authentication authentication) {
        Gangwon2 gangwon2 = gangwon2ApiService.getGangwon2DataById(id);
        Member member = memberService.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());

        gangwon2ApiService.like(gangwon2, member);
    }

    @PostMapping("/gangwon2/cancelLike/{id}")
    public void cancelLike(@PathVariable("id") Long id,Authentication authentication) {
        Gangwon2 gangwon2 = gangwon2ApiService.getGangwon2DataById(id);
        Member member = memberService.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());

        gangwon2ApiService.cancelLike(gangwon2, member);
    }

    @GetMapping("/gangwon2/getLikeCount/{id}")
    public int getLikeCount(@PathVariable("id") Long id) {
        Gangwon2 gangwon2 = gangwon2ApiService.getGangwon2DataById(id);

        return gangwon2.getLikes().size();
    }
}