package com.ll.traveler.domain.place.place.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ll.traveler.domain.place.place.entity.*;
import com.ll.traveler.domain.place.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/gyeonggi")
    public String fetchGyeonggiApi() throws IOException {
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

        placeService.mapJsonToGyeonggiList(result.toString());

        return result.toString();
    }

    @GetMapping("/gyeonggi2")
    public String fetchGyeonggi2Api() throws IOException {
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

        placeService.mapJsonToGyeonggi2List(result.toString());

        return result.toString();
    }

    @GetMapping("/gangwon")
    public String fetchGangwonApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = "https://api.odcloud.kr/api/15096638/v1/uddi:718fc83f-ef5d-41b0-9290-afbe366ad802?page=1&perPage=10&serviceKey=CXFCnn25be0lhjZXJtmWuH4WDFCB7Pasr%2Bci5Gz8OV3w9jTtGldsTpX%2BqEL7KF9pLgeXornjNYMWDA%2BHhXp4xQ%3D%3D";


        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String returnLine;

        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n\r");
        }
        con.disconnect();

        placeService.mapJsonToGangwonList(result.toString());

        return result.toString();
    }

    @GetMapping("/ulsan")
    public String fetchUlsanApi() throws IOException {

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

        placeService.mapXmlToUlsanList(result.toString());
        return jsonData;
    }

    @GetMapping("/places")
    public List<Place> showPlace() {
        List<Place> placeList = placeService.getAllPlaceData();
        return placeList;
    }

    @GetMapping("/places/{id}")
    public Place placeDetail(@PathVariable("id") Long id) {
        return placeService.getPlaceDataById(id);
    }

    @GetMapping("/places/search")
    public List<Place> search(){
        return placeService.getAllPlaceData();
    }

}

