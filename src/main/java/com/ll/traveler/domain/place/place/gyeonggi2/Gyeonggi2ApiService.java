package com.ll.traveler.domain.place.place.gyeonggi2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.traveler.domain.member.member.entity.Member;
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
                    .location(node.get("지역명").asText())
                    .address(node.get("주소").asText())
                    .contact(node.get("전화번호").asText())
                    .time(node.get("이용시간").asText())
                    .homePage(node.get("홈페이지").asText())
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

    public List<Gyeonggi2> searchAddress(String address) {
        return gyeonggi2ApiRepository.findAllByAddressContaining(address);
    }

    @Transactional
    public void like(Gyeonggi2 gyeonggi2, Member member) {
        gyeonggi2.like(member);
    }

    @Transactional
    public void cancelLike(Gyeonggi2 gyeonggi2, Member member) {
        gyeonggi2.cancelLike(member);
    }
}