package com.ll.traveler.domain.place.place.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ll.traveler.domain.member.member.service.MemberService;
import com.ll.traveler.domain.place.place.gangwon.Gangwon2;
import com.ll.traveler.domain.place.place.gangwon.Gangwon2ApiRepository;
import com.ll.traveler.domain.place.place.gangwon.Gangwon2ApiService;
import com.ll.traveler.domain.place.place.gyeonggi.Gyeonggi;
import com.ll.traveler.domain.place.place.gyeonggi.GyeonggiService;
import com.ll.traveler.domain.place.place.gyeonggi2.Gyeonggi2;
import com.ll.traveler.domain.place.place.gyeonggi2.Gyeonggi2ApiService;
import com.ll.traveler.domain.place.place.ulsan.Ulsan;
import com.ll.traveler.domain.place.place.ulsan.UlsanApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
public class PlaceController {
    @RestController
    @RequiredArgsConstructor
    public class Gangwon2ApiController {

        private final Gangwon2ApiService gangwon2ApiService;
        private final Gangwon2ApiRepository gangwon2ApiRepository;
        private final GyeonggiService gyeonggiService;
        private final MemberService memberService;
        private final UlsanApiService ulsanApiService;
        private final Gyeonggi2ApiService gyeonggi2ApiService;


        @GetMapping("/apiGangwon2")
        public String callGangwon2Api() throws IOException {
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

            gangwon2ApiService.mapJsonToGyeonggi2List(result.toString());

            return result.toString();
        }

        @GetMapping("/apiGyeonggi")
        public String callGyeonggiApi() throws IOException {
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

        @GetMapping("/apiGyeonggi2")
        public String callGyeonggi2Api() throws IOException {
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

        @GetMapping("/apiUlsan")
        public String callUlsanApi() throws IOException {

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


        @GetMapping("gyeonggi")
        public List<Gyeonggi> showGyeonggi() {
            List<Gyeonggi> gyeonggiList = gyeonggiService.getAllGyeonggiData();
            return gyeonggiList;
        }


        @GetMapping("/gangwon2")
        public List<Gangwon2> showGangwon2() {
            List<Gangwon2> gangwon2List = gangwon2ApiService.getAllGangwon2Data();
            return gangwon2List;
        }

        @GetMapping("/ulsan")
        public List<Ulsan> showUlsan() {
            List<Ulsan> ulsanList = ulsanApiService.getAllUlsanData();
            return ulsanList;
        }

        @GetMapping("gyeonggi2")
        public List<Gyeonggi2> showGyeonggi2() {
            List<Gyeonggi2> gyeonggi2List = gyeonggi2ApiService.getAllGyeonggi2Data();
            return gyeonggi2List;
        }

    }
}
