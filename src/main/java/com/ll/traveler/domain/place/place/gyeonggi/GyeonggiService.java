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

            for (JsonNode node : rowNode) {
                Gyeonggi gyeonggi = new Gyeonggi();
                gyeonggi.setParkNm(getStringValue(node, "PARK_NM"));
                gyeonggi.setSignguNm(getStringValue(node, "SIGNGU_NM"));
                gyeonggi.setEmdNm(getStringValue(node, "EMD_NM"));
                gyeonggi.setAr(getStringValue(node, "AR"));
                gyeonggi.setCmgpermsnTm(getStringValue(node, "CMG_PERMSN_TM"));
                gyeonggi.setCmgpermsnDay(getStringValue(node, "CMG_PERMSN_DAY"));
                gyeonggi.setOpertinstNm(getStringValue(node, "OPERT_INST_NM"));
                gyeonggi.setReprsntTelNo(getStringValue(node, "REPRSNT_TELNO"));
                gyeonggi.setExpn(getStringValue(node, "EXPN"));
                gyeonggi.setUtlzChrg(getStringValue(node, "UTLZ_CHRG"));
                gyeonggi.setPartclrMatr(getStringValue(node, "PARTCLR_MATR"));
                gyeonggi.setImageNm(getStringValue(node, "IMAGE_NM"));
                gyeonggi.setRefineWgs84Lat(getStringValue(node, "REFINE_WGS84_LAT"));
                gyeonggi.setRefineWgs84Logt(getStringValue(node, "REFINE_WGS84_LOGT"));

                gyeonggiList.add(gyeonggiRepository.save(gyeonggi));
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
    public List<Gyeonggi> getAllGyeonggiData(){
        return gyeonggiRepository.findAll();
    }

    public Gyeonggi getGyeonggiDataById(Long id) {
        Optional<Gyeonggi> gyeonggiOptional = gyeonggiRepository.findById(id);
        return gyeonggiOptional.orElse(null);
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