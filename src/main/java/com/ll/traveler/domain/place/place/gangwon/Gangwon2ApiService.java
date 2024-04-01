package com.ll.traveler.domain.place.place.gangwon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.place.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ll.traveler.domain.place.place.entity.Gangwon2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Gangwon2ApiService {

//    private final Gangwon2ApiRepository gangwon2ApiRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public List<Place> mapJsonToGyeonggi2List(String jsonData) throws IOException {
        List<Place> gangwon2List = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode dataListNode = rootNode.get("data");

        for (JsonNode node : dataListNode) {
            Place place = Gangwon2.builder()
                    .division(node.get("업체구분").asText())
                    .name(node.get("업체명").asText())
                    .address(node.get("도로명 주소").asText())
                    .latitude(node.get("위도").asText())
                    .longitude(node.get("경도").asText())
                    .contact(node.get("연락처").asText())
                    .build();

            gangwon2List.add(placeRepository.save(place));
        }

        return gangwon2List;
    }

    public List<Place> getAllGangwon2Data() {
        return placeRepository.findAllByIdLessThan(11);
    }

    public Place getGangwon2DataById(Long id) {
        Optional<Place> gangwon2Optional = placeRepository.findById(id);
        if(gangwon2Optional.isPresent()) {
            return gangwon2Optional.get();
        }
       return null;
    }

}
