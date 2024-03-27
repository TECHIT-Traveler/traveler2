package com.ll.traveler.domain.place.place.gangwon;


import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/apiGangwon2/name/{name}")
    public List<Gangwon2> searchName(@PathVariable String name) {
        return gangwon2ApiService.searchName(name);
    }

    @GetMapping("/apiGangwon2/address/{address}")
    public List<Gangwon2> searchAddress(@PathVariable String address) {
        return gangwon2ApiService.searchAddress(address);
    }
}