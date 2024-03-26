package com.ll.traveler.domain.place.place.gyeonggi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GyeonggiApiController {

    private final GyeonggiService gyeonggiService;

    @GetMapping("/apiGyeonggi")
    public String showApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlstr = "https://openapi.gg.go.kr/OTHERHALFANIENTPARK?" +
                "Key=6ec88296e3154f8d8eeec31031906fad" +
                "&pIndex=1" +
                "&pSize=50" +
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

        gyeonggiService.mapJsonToGyeonggiList(result.toString());

        return result.toString();
    }
    @GetMapping("gyeonggi")
    public List<Gyeonggi> showGyeonggi() {
        List<Gyeonggi> gyeonggiList = gyeonggiService.getAllGyeonggiData();
        return gyeonggiList;
    }

    @GetMapping("/gyeonggi/{id}")
    public Gyeonggi detail(@PathVariable("id") Long id) {
        return gyeonggiService.getGyeonggiDataById(id);
    }

}
