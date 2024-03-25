package com.ll.traveler.domain.place.place.gyeonggi2;

import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Gyeonggi2 extends IdEntity {

    private String 지역명; // 등록일자
    private String 주소; // 등록일자
    private String 전화번호; // 등록일자
    private String 이용시간; // 등록일자
    private String 홈페이지; // 등록일자

}