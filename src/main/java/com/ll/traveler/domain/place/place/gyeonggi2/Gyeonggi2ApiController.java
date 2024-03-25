package com.ll.traveler.domain.place.place.gyeonggi2;


import com.ll.traveler.domain.place.place.gyeonggi.Gyeonggi;
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
public class Gyeonggi2ApiController {

    private final Gyeonggi2ApiService gyeonggi2ApiService;


    @GetMapping("/apiGyeonggi2")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = "https://api.odcloud.kr/api/15103199/v1/uddi:e352b824-15d9-49c0-9c50-ff01c13805cf?page=1&perPage=10&returnType=xml&serviceKey=CXFCnn25be0lhjZXJtmWuH4WDFCB7Pasr%2Bci5Gz8OV3w9jTtGldsTpX%2BqEL7KF9pLgeXornjNYMWDA%2BHhXp4xQ%3D%3D";


        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String returnLine;

        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n\r");
        }
        con.disconnect();

        gyeonggi2ApiService.mapJsonToGyeonggi2List(result.toString());

        return result.toString();
    }

    @GetMapping("gyeonggi2")
    public List<Gyeonggi2> showGyeonggi2() {
        List<Gyeonggi2> gyeonggi2List = gyeonggi2ApiService.getAllGyeonggi2Data();
        return gyeonggi2List;
    }
    @GetMapping("/gyeonggi2/{id}")
    public Gyeonggi2 detail2(@PathVariable("id") Long id) {
        return gyeonggi2ApiService.getGyeonggi2DataById(id);
    }
}