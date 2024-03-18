package com.ll.traveler.domain.place.place.gyeonggi;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequiredArgsConstructor
public class PlayGroundApiController {

    private final PlayGroundService service;


    @GetMapping("/apiGyeonggi")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlstr = "https://openapi.gg.go.kr/OTHERHALFANIENTPARK?" +
                "serviceKey=6ec88296e3154f8d8eeec31031906fad" +
                "pIndex=1" +
                "&pSize=10" +
                "&type=json";

        URL url = new URL(urlstr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String retrunLine;

        while ((retrunLine = br.readLine()) !=null){
            result.append(retrunLine + "\n\r");
        }
        con.disconnect();

        service.mapJsonToPlayGroundList(result.toString());

        return result.toString();
    }

}