package com.ll.traveler.domain.place.place.gyeonggi2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.place.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ll.traveler.domain.place.place.entity.Gyeonggi2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Gyeonggi2ApiService {

//    private final Gyeonggi2ApiRepository gyeonggi2ApiRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public List<Place> mapJsonToGyeonggi2List(String jsonData) throws IOException {
        List<Place> gyeonggi2List = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode dataListNode = rootNode.get("data");

        for (JsonNode node : dataListNode) {
            Place place = Gyeonggi2.builder()
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

    private String getStringValue(JsonNode node, String fieldName) {
        JsonNode fieldValue = node.get(fieldName);
        return fieldValue != null && !fieldValue.isNull() ? fieldValue.asText() : "-";
    }

    public List<Place> getAllGyeonggi2Data(){
        return placeRepository.findAll();
    }

    public Place getGyeonggi2DataById(Long id) {
        Optional<Place> gyeonggi2Optional = placeRepository.findById(id);
        return gyeonggi2Optional.orElse(null);
    }

}
