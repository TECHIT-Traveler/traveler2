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

            // 각각의 객체 파싱하여 데이터베이스에 저장
            for (JsonNode node : rowNode) {
                Gyeonggi gyeonggi = Gyeonggi.builder()
                        .PARK_NM(node.get("PARK_NM").asText())
                        .SIGNGU_NM(node.get("SIGNGU_NM").asText())
                        .EMD_NM(node.get("EMD_NM").asText())
                        .AR(node.get("AR").asText())
                        .CMGPERMSN_TM(node.get("CMG_PERMSN_TM").asText())
                        .CMGPERMSN_DAY(node.get("CMG_PERMSN_DAY").asText())
                        .OPERTINST_NM(node.get("OPERT_INST_NM").asText())
                        .REPRSNT_TELNO(node.get("REPRSNT_TELNO").asText())
                        .EXPN(node.get("EXPN").asText())
                        .UTLZ_CHRG(node.get("UTLZ_CHRG").asText())
                        .PARTCLR_MATR(node.get("PARTCLR_MATR").asText())
                        .IMAGE_NM(node.get("IMAGE_NM").asText())
                        .REFINE_WGS84_LAT(node.get("REFINE_WGS84_LAT").asText())
                        .REFINE_WGS84_LOGT(node.get("REFINE_WGS84_LOGT").asText())
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

    @Transactional
    public void save(Gyeonggi gyeonggi, Member member) {
        gyeonggi.save(member);
    }

    @Transactional
    public void cancelSave(Gyeonggi gyeonggi, Member member) {
        gyeonggi.cancelSave(member);
    }

}