package com.ll.traveler.domain.place.place.gyeonggi;

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

        for (JsonNode node : rowNode ) {

            Gyeonggi gyeonggi = Gyeonggi.builder()
                    .parkNm(node.get("PARK_NM").asText())
                    .signguNm(node.get("SIGNGU_NM").asText())
                    .emdNm(node.get("EMD_NM").asText())
                    .ar(node.get("AR").asText())
                    .cmgpermsnTm(node.get("CMG_PERMSN_TM").asText())
                    .cmgpermsnDay(node.get("CMG_PERMSN_DAY").asText())
                    .opertinstNm(node.get("OPERT_INST_NM").asText())
                    .reprsntTelNo(node.get("REPRSNT_TELNO").asText())
                    .expn(node.get("EXPN").asText())
                    .utlzChrg(node.get("UTLZ_CHRG").asText())
                    .partclrMatr(node.get("PARTCLR_MATR").asText())
                    .imageNm(node.get("IMAGE_NM").asText())
                    .refineWgs84Lat(node.get("REFINE_WGS84_LAT").asText())
                    .refineWgs84Logt(node.get("REFINE_WGS84_LOGT").asText())
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

    @Transactional
    public void like(Gyeonggi gyeonggi, Member member) {
        gyeonggi.like(member);
    }

    @Transactional
    public void cancelLike(Gyeonggi gyeonggi, Member member) {
        gyeonggi.cancelLike(member);
    }

}