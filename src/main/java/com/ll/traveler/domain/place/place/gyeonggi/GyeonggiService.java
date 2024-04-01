package com.ll.traveler.domain.place.place.gyeonggi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.place.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import com.ll.traveler.domain.place.place.entity.Gyeonggi;

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
public class GyeonggiService {

    private final PlaceRepository placeRepository;

    @Transactional
    public List<Place> mapJsonToGyeonggiList(String jsonData) throws IOException {

        List<Place> gyeonggiList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            // "OTHERHALFANIENTPARK" 노드 찾기
            JsonNode dataListNode = rootNode.get("OTHERHALFANIENTPARK");

            // "row" 노드 찾기
            JsonNode rowNode = dataListNode.get(1).get("row");

            for (JsonNode node : rowNode) {
                Place gyeonggi = Gyeonggi.builder()
                        .name(getStringValue(node, "PARK_NM"))
                        .signguNm(getStringValue(node, "SIGNGU_NM"))
                        .emdNm(getStringValue(node, "EMD_NM"))
                        .ar(getStringValue(node, "AR"))
                        .cmgpermsnTm(getStringValue(node, "CMG_PERMSN_TM"))
                        .cmgpermsnDay(getStringValue(node, "CMG_PERMSN_DAY"))
                        .opertinstNm(getStringValue(node, "OPERT_INST_NM"))
                        .contact(getStringValue(node, "REPRSNT_TELNO"))
//                        .expn(getStringValue(node, "EXPN"))
//                        .utlzChrg(getStringValue(node, "UTLZ_CHRG"))
                        .partclrMatr(getStringValue(node, "PARTCLR_MATR"))
                        .imageNm(getStringValue(node, "IMAGE_NM"))
                        .build();
//                Gyeonggi gyeonggi = new Gyeonggi();
//                gyeonggi.setParkNm(getStringValue(node, "PARK_NM"));
//                gyeonggi.setSignguNm(getStringValue(node, "SIGNGU_NM"));
//                gyeonggi.setEmdNm(getStringValue(node, "EMD_NM"));
//                gyeonggi.setAr(getStringValue(node, "AR"));
//                gyeonggi.setCmgpermsnTm(getStringValue(node, "CMG_PERMSN_TM"));
//                gyeonggi.setCmgpermsnDay(getStringValue(node, "CMG_PERMSN_DAY"));
//                gyeonggi.setOpertinstNm(getStringValue(node, "OPERT_INST_NM"));
//                gyeonggi.setReprsntTelNo(getStringValue(node, "REPRSNT_TELNO"));
//                gyeonggi.setExpn(getStringValue(node, "EXPN"));
//                gyeonggi.setUtlzChrg(getStringValue(node, "UTLZ_CHRG"));
//                gyeonggi.setPartclrMatr(getStringValue(node, "PARTCLR_MATR"));
//                gyeonggi.setImageNm(getStringValue(node, "IMAGE_NM"));

                gyeonggiList.add(placeRepository.save(gyeonggi));
            }
        } catch (Exception e) {
            log.error("Failed to save database", e);
        }

        return gyeonggiList;
    }
    private String getStringValue(JsonNode node, String fieldName) {
        JsonNode fieldValue = node.get(fieldName);
        return fieldValue != null && !fieldValue.isNull() ? fieldValue.asText() : "-";
    }
    public List<Place> getAllGyeonggiData(){
        return placeRepository.findAll();
    }

    public Place getGyeonggiDataById(Long id) {
        Optional<Place> gyeonggiOptional = placeRepository.findById(id);
        return gyeonggiOptional.orElse(null);
    }

}
