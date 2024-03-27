package com.ll.traveler.domain.place.place.gyeonggi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.traveler.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});

        List<Map<String, String>> targetValues = (List<Map<String, String>>) ((Map<String, List<Map<String, String>>>) ((List<Object>) dataMap.get("OTHERHALFANIENTPARK")).get(1)).get("row");



        for (Map<String, String> val : targetValues ) {

            Gyeonggi entity = Gyeonggi.builder()
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
                    .REFINE_WGS84_LAT(val.get("REFINE_WGS84_LAT"))
                    .REFINE_WGS84_LOGT(val.get("REFINE_WGS84_LOGT"))
                    .build();

            gyeonggiRepository.save(entity);

        }
        return gyeonggiList;
    }
    public List<Gyeonggi> getAllGyeonggiData(){
        return gyeonggiRepository.findAll();
    }

    public Gyeonggi getGyeonggiDataById(Long id) {
        Optional<Gyeonggi> gyeonggiOptional = gyeonggiRepository.findById(id);
        if(gyeonggiOptional.isPresent()) {
            return gyeonggiOptional.get();
        }
        return null;
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