package com.ll.traveler.domain.place.place.gyeonggi;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.CascadeType.ALL;


import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Gyeonggi extends IdEntity {
    private String parkNm; // 공원명
    private String signguNm; // 시군구 명
    private String emdNm; // 읍면동명
    private String ar; // 규모시설면적
    private String cmgpermsnTm; // 출입허용시간
    private String cmgpermsnDay	; // 출입허용일
    private String opertinstNm; // 운영기관명
    private String reprsntTelNo; // 대표전화번호
    private String expn; //	비용
    private String utlzChrg; // 이용요금
    private String partclrMatr; // 특이사항
    private String imageNm; // 이미지

    // 추가적으로 위도와 경도 필드를 정의해야 합니다.
    private String refineWgs84Lat; // 위도
    private String refineWgs84Logt; // 경도

    @OneToMany(mappedBy = "post", cascade =  ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<GyeonggiLike> likes = new ArrayList<>();

    public boolean hasLike(Member member) {
        return likes.stream()
                .anyMatch(like -> like.getMember().equals(member));
    }

    public void like(Member member) {
        likes.add(
                GyeonggiLike.builder()
                        .member(member)
                        .post(this)
                        .build()
        );
    }

    public void cancelLike(Member member) {
        likes.removeIf(like -> like.getMember().equals(member));
    }


}
