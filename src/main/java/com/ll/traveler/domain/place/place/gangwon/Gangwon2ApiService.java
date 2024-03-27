package com.ll.traveler.domain.place.place.gangwon;

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
                    .division(node.get("업체구분").asText())
                    .name(node.get("업체명").asText())
                    .lotAddress(node.get("지번 주소").asText())
                    .streetAddress(node.get("도로명 주소").asText())
                    .latitude(node.get("위도").asText())
                    .longitude(node.get("경도").asText())
                    .contact(node.get("연락처").asText())
                    .build();

            gangwon2List.add(gangwon2ApiRepository.save(gangwon2));
        }

        return gangwon2List;
    }

    public List<Gangwon2> getAllGangwon2Data() {
        return gangwon2ApiRepository.findAllByIdLessThan(11);
    }

    public Gangwon2 getGangwon2DataById(Long id) {
        Optional<Gangwon2> gangwon2Optional = gangwon2ApiRepository.findById(id);
        if(gangwon2Optional.isPresent()) {
            return gangwon2Optional.get();
        }
       return null;
    }

    @Transactional
    public void like(Gangwon2 gangwon2, Member member) {
        gangwon2.like(member);
    }

    @Transactional
    public void cancelLike(Gangwon2 gangwon2, Member member) {
        gangwon2.cancelLike(member);
    }

    public List<Gangwon2> searchName(String name) {
        return gangwon2ApiRepository.findAllByNameContaining(name);
    }

    public List<Gangwon2> searchAddress(String address) {
        return gangwon2ApiRepository.findAllByStreetAddressContaining(address);
    }

}
