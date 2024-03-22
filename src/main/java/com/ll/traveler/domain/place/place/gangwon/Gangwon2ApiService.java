package com.ll.traveler.domain.place.place.gangwon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Gangwon2ApiService {

    private final Gangwon2ApiRepository gangwon2ApiRepository;

    @Transactional
    public List<Gangwon2> mapJsonToGyeonggi2List(String jsonData) throws IOException {
        List<Gangwon2> gangwon2List = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode dataListNode = rootNode.get("data");


        for (JsonNode node : dataListNode) {
            Gangwon2 gangwon2 = Gangwon2.builder()
                    .업체구분(node.get("업체구분").asText())
                    .업체명(node.get("업체명").asText())
                    .지번_주소(node.get("지번 주소").asText())
                    .도로명_주소(node.get("도로명 주소").asText())
                    .위도(node.get("위도").asText())
                    .경도(node.get("경도").asText())
                    .연락처(node.get("연락처").asText())
                    .build();

            gangwon2List.add(gangwon2ApiRepository.save(gangwon2));
        }

        return gangwon2List;
    }
    public List<Gangwon2> getAllGangwon2Data(){
        return gangwon2ApiRepository.findAll();
    }
}
