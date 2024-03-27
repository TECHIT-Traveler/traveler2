package com.ll.traveler.domain.place.place.gyeonggi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class GyeonggiService {

    private final GyeonggiRepository gyeonggiRepository;

    @Transactional
    public List<Gyeonggi> mapJsonToGyeonggiList(String jsonData) throws IOException {

        List<Gyeonggi> gyeonggiList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            // "OTHERHALFANIENTPARK" 노드 찾기
            JsonNode dataListNode = rootNode.get("OTHERHALFANIENTPARK");

            // "row" 노드 찾기
            JsonNode rowNode = dataListNode.get(1).get("row");

        for (JsonNode val : rowNode ) {

            Gyeonggi gyeonggi = Gyeonggi.builder()
                    .parkNm(val.get("PARK_NM").asText())
                    .signguNm(val.get("SIGNGU_NM").asText())
                    .emdNm(val.get("EMD_NM").asText())
                    .ar(val.get("AR").asText())
                    .cmgpermsnTm(val.get("CMGPERMSN_TM").asText())
                    .cmgpermsnDay(val.get("CMGPERMSN_DAY").asText())
                    .opertinstNm(val.get("OPERTINST_NM").asText())
                    .reprsntTelNo(val.get("REPRSNT_TELNO").asText())
                    .expn(val.get("EXPN").asText())
                    .utlzChrg(val.get("UTLZ_CHRG").asText())
                    .partclrMatr(val.get("PARTCLR_MATR").asText())
                    .imageNm(val.get("IMAGE_NM").asText())
                    .build();

                gyeonggiList.add(gyeonggiRepository.save(gyeonggi)); // 데이터베이스에 저장 후 리스트에 추가
            }
        } catch (Exception e) {
            log.error("Failed to save database", e);
        }

        return gyeonggiList;
    }
    public List<Gyeonggi> getAllGyeonggiData(){
        return gyeonggiRepository.findAll();
    }

    public Gyeonggi getGyeonggiDataById(Long id) {
        Optional<Gyeonggi> gyeonggiOptional = gyeonggiRepository.findById(id);
        return gyeonggiOptional.orElse(null);
    }

    public List<Gyeonggi> searchPark(String park) {
        return gyeonggiRepository.findAllByParkNmContaining(park);
    }

    public List<Gyeonggi> searchSigngu(String signgu) {
        return gyeonggiRepository.findAllBySignguNmContaining(signgu);
    }

    public List<Gyeonggi> searchEmd(String emd) {
        return gyeonggiRepository.findAllByEmdNmContaining(emd);
    }
}