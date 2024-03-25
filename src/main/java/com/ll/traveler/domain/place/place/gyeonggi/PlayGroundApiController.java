package com.ll.traveler.domain.place.place.gyeonggi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequiredArgsConstructor
public class PlayGroundApiController {

    private final PlayGroundService service;

    @GetMapping("/apiGyeonggi")
    public String showPlaygrounds(Model model) throws IOException {
        StringBuilder result = new StringBuilder();

        String urlstr = "https://openapi.gg.go.kr/OTHERHALFANIENTPARK?" +
                "Key=6ec88296e3154f8d8eeec31031906fad" +
                "&pIndex=1" +
                "&pSize=30" +
                "&type=json";

        URL url = new URL(urlstr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String returnLine;

        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine).append("\n");
        }
        con.disconnect();

        service.mapJsonToPlayGroundList(result.toString());

        // 데이터를 모델에 추가하여 HTML 파일에서 사용할 수 있도록 함
        model.addAttribute("jsonData", result.toString());

        // HTML 파일의 경로를 반환
        return "domain/place/place/gyeonggi";
    }
}
