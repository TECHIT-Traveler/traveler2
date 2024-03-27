package com.ll.traveler.domain.place.place.gyeonggi;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.traveler.domain.place.place.gyeonggi2.Gyeonggi2;
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
public class GyeonggiService {

    private final GyeonggiRepository gyeonggiRepository;

    @Transactional
    public List<Gyeonggi> mapJsonToGyeonggiList(String jsonData) throws IOException {

        List<Gyeonggi> gyeonggiList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});

        List<Map<String, String>> targetValues = (List<Map<String, String>>) ((Map<String, List<Map<String, String>>>) ((List<Object>) dataMap.get("OTHERHALFANIENTPARK")).get(1)).get("row");



        for (Map<String, String> val : targetValues ) {

            Gyeonggi entity = Gyeonggi.builder()
                    .parkNm(val.get("PARK_NM"))
                    .signguNm(val.get("SIGNGU_NM"))
                    .emdNm(val.get("EMD_NM"))
                    .ar(val.get("AR"))
                    .cmgpermsnTm(val.get("CMGPERMSN_TM"))
                    .cmgpermsnDay(val.get("CMGPERMSN_DAY"))
                    .opertinstNm(val.get("OPERTINST_NM"))
                    .reprsntTelNo(val.get("REPRSNT_TELNO"))
                    .expn(val.get("EXPN"))
                    .utlzChrg(val.get("UTLZ_CHRG"))
                    .partclrMatr(val.get("PARTCLR_MATR"))
                    .imageNm(val.get("IMAGE_NM"))
                    .build();

            gyeonggiRepository.save(entity);

        }
        return gyeonggiList;
    }
    public List<Gyeonggi> getAllGyeonggiData(){
        return gyeonggiRepository.findAll();
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