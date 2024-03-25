package com.ll.traveler.domain.place.place.gyeonggi;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor // 필드 주입을 위한 생성자 생성
@Transactional(readOnly = true) // 읽기전용
public class PlayGroundService {

    private final PlayGroundRepository playGroundRepository;

    @Transactional
    public List<PlayGround> mapJsonToPlayGroundList(String jsonData) throws IOException {

        List<PlayGround> playGroundList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});

        List<Map<String, String>> targetValues = (List<Map<String, String>>) ((Map<String, List<Map<String, String>>>) ((List<Object>) dataMap.get("OTHERHALFANIENTPARK")).get(1)).get("row");



        for (Map<String, String> val : targetValues ) {

            PlayGround entity = PlayGround.builder()
                    .PARK_NM(val.get("PARK_NM"))
                    .SIGNGU_NM(val.get("SIGNGU_NM"))
                    .EMD_NM(val.get("EMD_NM"))
                    .AR(val.get("AR"))
                    .CMGPERMSN_TM(val.get("CMGPERMSN_TM"))
                    .CMGPERMSN_DAY(val.get("CMGPERMSN_DAY"))
                    .OPERTINST_NM(val.get("OPERTINST_NM"))
                    .REPRSNT_TELNO(val.get("REPRSNT_TELNO"))
                    .EXPN(val.get("EXPN"))
                    .UTLZ_CHRG(val.get("UTLZ_CHRG"))
                    .PARTCLR_MATR(val.get("PARTCLR_MATR"))
                    .IMAGE_NM(val.get("IMAGE_NM"))
                    .build();

            playGroundRepository.save(entity);

        }


        return playGroundList;
    }


}