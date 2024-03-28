package com.ll.traveler.domain.place.place.gyeonggi;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Gyeonggi extends IdEntity {
    private String PARK_NM; // 공원명
    private String SIGNGU_NM; // 시군구 명
    private String EMD_NM; // 읍면동명
    private String AR; // 규모시설면적
    private String CMGPERMSN_TM; // 출입허용시간
    private String CMGPERMSN_DAY	; // 출입허용일
    private String OPERTINST_NM; // 운영기관명
    private String REPRSNT_TELNO; // 대표전화번호
    private String EXPN; //	비용
    private String UTLZ_CHRG; // 이용요금
    private String PARTCLR_MATR; // 특이사항
    private String IMAGE_NM; // 이미지

    // 추가적으로 위도와 경도 필드를 정의해야 합니다.
    private String REFINE_WGS84_LAT; // 위도
    private String REFINE_WGS84_LOGT; // 경도

    @OneToMany(mappedBy = "gyeonggi", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<GyeonggiSave> saves = new ArrayList<>();

    public boolean hasSave(Member member) {
        return saves.stream()
                .anyMatch(save -> save.getMember().equals(member));
    }

    public void save(Member member) {
        saves.add(
                GyeonggiSave.builder()
                        .member(member)
                        .gyeonggi(this)
                        .build()
        );
    }

    public void cancelSave(Member member) {
        saves.removeIf(Save -> Save.getMember().equals(member));
    }
}
