package com.ll.traveler.domain.place.place.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ll.traveler.domain.place.place.entity.*;
import com.ll.traveler.domain.place.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor // 필드 주입을 위한 생성자 생성
@Transactional(readOnly = true) // 읽기전용
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public List<Place> mapJsonToGyeonggiList(String jsonData) throws IOException {

        List<Place> gyeonggiList = new ArrayList<>();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            // "OTHERHALFANIENTPARK" 노드 찾기
            JsonNode dataListNode = rootNode.get("OTHERHALFANIENTPARK");

            // "row" 노드 찾기
            JsonNode rowNode = dataListNode.get(1).get("row");

            for (JsonNode node : rowNode) {
                Place place = Gyeonggi.builder()
                        .name(getStringValue(node, "PARK_NM"))
                        .address(getStringValue(node, "SIGNGU_NM") + " " + getStringValue(node, "EMD_NM"))
                        .ar(getStringValue(node, "AR"))
                        .cmgpermsnTm(getStringValue(node, "CMG_PERMSN_TM"))
                        .cmgpermsnDay(getStringValue(node, "CMG_PERMSN_DAY"))
                        .opertinstNm(getStringValue(node, "OPERT_INST_NM"))
                        .contact(getStringValue(node, "REPRSNT_TELNO"))
                        .partclrMatr(getStringValue(node, "PARTCLR_MATR"))
                        .imageNm(getStringValue(node, "IMAGE_NM"))
                        .build();

                gyeonggiList.add(placeRepository.save(place));
            }
        return gyeonggiList;
    }

    public List<Place> getAllPlaceData(){
        return placeRepository.findAll();
    }

    public Place getPlaceDataById(Long id) {
        Optional<Place> placeOptional = placeRepository.findById(id);
        return placeOptional.orElse(null);
    }


// 경기 1 끝
    @Transactional
    public List<Place> mapJsonToGyeonggi2List(String jsonData) throws IOException {
        List<Place> gyeonggi2List = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode dataListNode = rootNode.get("data");

        for (JsonNode node : dataListNode) {
            Place place = Gyeonggi2.builder()
                    .name(getStringValue(node, "업체명"))
                    .location(getStringValue(node, "지역명"))
                    .address(getStringValue(node, "주소"))
                    .contact(getStringValue(node, "전화번호"))
                    .time(getStringValue(node, "이용시간"))
                    .homePage(getStringValue(node, "홈페이지"))
                    .build();

            gyeonggi2List.add(placeRepository.save(place));
        }

        return gyeonggi2List;
    }

    // 경기 2 끝
    @Transactional
    public List<Place> mapJsonToGangwonList(String jsonData) throws IOException {
        List<Place> gangwonList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode dataListNode = rootNode.get("data");

        for (JsonNode node : dataListNode) {
            Place place = Gangwon.builder()
                    .division(getStringValue(node, "업체구분"))
                    .name(getStringValue(node, "업체명"))
                    .address(getStringValue(node, "도로명 주소"))
                    .latitude(getStringValue(node, "위도"))
                    .longitude(getStringValue(node, "경도"))
                    .contact(getStringValue(node, "연락처"))
                    .build();

            gangwonList.add(placeRepository.save(place));
        }

        return gangwonList;
    }


// 강원 끝
    @Transactional
    public List<Place> mapXmlToUlsanList(String xmlData) throws IOException {
        List<Place> ulsanList = new ArrayList<>();

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode rootNode = xmlMapper.readTree(xmlData);

        JsonNode bodyNode = rootNode.get("body");
        JsonNode dataListNode = bodyNode.get("data");
        JsonNode listNode = dataListNode.get("list");

        for (JsonNode node : listNode) {
            Place place = Ulsan.builder()
                    .unqId(getStringValue(node, "unqId"))
                    .city(getStringValue(node, "city"))
                    .name(getStringValue(node, "facility"))
                    .contact(getStringValue(node, "tel"))
                    .cls(getStringValue(node, "cls"))
                    .remarks(getStringValue(node, "remarks"))
                    .zipCode(getStringValue(node, "zipCode"))
                    .address(getStringValue(node, "streetNameAddress"))
                    .address(getStringValue(node, "address"))
                    .latitude(getStringValue(node, "lat"))
                    .longitude(getStringValue(node, "lng"))
                    .rgstDate(getStringValue(node, "rgstDate"))
                    .build();

            ulsanList.add(placeRepository.save(place));
        }

        return ulsanList;
    }

    private String getStringValue(JsonNode node, String fieldName) {
        JsonNode fieldValue = node.get(fieldName);
        if (fieldValue != null && !fieldValue.isNull() && !fieldValue.asText().equals("null")) {
            return fieldValue.asText();
        } else {
            return null;
        }
    }
}
