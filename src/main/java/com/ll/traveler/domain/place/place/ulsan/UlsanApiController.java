package com.ll.traveler.domain.place.place.ulsan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ll.traveler.domain.place.place.gangwon.Gangwon2;
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
public class UlsanApiController {

    private final UlsanApiService ulsanApiService;

    @GetMapping("/apiUlsan")
    public String callApi() throws IOException {

        StringBuilder result = new StringBuilder();

        String urlStr = "http://apis.data.go.kr/6310000/petFriendlyFacilities/getPetFriendlyFacilitiesList?" +
                "ServiceKey=CXFCnn25be0lhjZXJtmWuH4WDFCB7Pasr%2Bci5Gz8OV3w9jTtGldsTpX%2BqEL7KF9pLgeXornjNYMWDA%2BHhXp4xQ%3D%3D" +
                "&type=xml" +
                "&pageNo=1" +
                "&numOfRows=50";
        URL url = new URL(urlStr);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"))) {
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine).append("\n");
            }
        } finally {
            urlConnection.disconnect();
        }

        // XML 데이터를 JSON으로 변환
        ObjectMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(result.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(jsonNode);

        ulsanApiService.mapXmlToUlsanList(result.toString());
        return jsonData;
    }

    @GetMapping("/ulsan")
    public List<Ulsan> showUlsan() {
        List<Ulsan> ulsanList = ulsanApiService.getAllUlsanData();
        return ulsanList;
    }

    @GetMapping("/ulsan/facility/{facility}")
    public List<Ulsan> searchFacility(@PathVariable String facility) {
        return ulsanApiService.searchFacility(facility);
    }

    @GetMapping("/ulsan/city/{city}")
    public List<Ulsan> searchCity(@PathVariable String city) {
        return ulsanApiService.searchCity(city);
    }

}