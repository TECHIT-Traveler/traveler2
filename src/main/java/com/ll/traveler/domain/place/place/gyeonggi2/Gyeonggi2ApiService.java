package com.ll.traveler.domain.place.place.gyeonggi2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.traveler.domain.place.place.gyeonggi.Gyeonggi;
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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Gyeonggi2ApiService {

    private final Gyeonggi2ApiRepository gyeonggi2ApiRepository;

    @Transactional
    public List<Gyeonggi2> mapJsonToGyeonggi2List(String jsonData) throws IOException {
        List<Gyeonggi2> gyeonggi2List = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode dataListNode = rootNode.get("data");

        for (JsonNode node : dataListNode) {
            Gyeonggi2 gyeonggi2 = Gyeonggi2.builder()
                    .업체명(node.get("업체명").asText())
                    .지역명(node.get("지역명").asText())
                    .주소(node.get("주소").asText())
                    .전화번호(node.get("전화번호").asText())
                    .이용시간(node.get("이용시간").asText())
                    .홈페이지(node.get("홈페이지").asText())
                    .build();

            gyeonggi2List.add(gyeonggi2ApiRepository.save(gyeonggi2));
        }

        return gyeonggi2List;
    }

    public List<Gyeonggi2> getAllGyeonggi2Data(){
        return gyeonggi2ApiRepository.findAll();
    }
    public Gyeonggi2 getGyeonggi2DataById(Long id) {
        Optional<Gyeonggi2> gyeonggi2Optional = gyeonggi2ApiRepository.findById(id);
        return gyeonggi2Optional.orElse(null);
    }
}