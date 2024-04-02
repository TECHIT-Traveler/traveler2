package com.ll.traveler.domain.place.place.entity;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Gyeonggi extends Place {
    private String signguNm; // 시군구 명
    private String emdNm; // 읍면동명
    private String ar; // 규모시설면적
    private String cmgpermsnTm; // 출입허용시간
    private String cmgpermsnDay	; // 출입허용일
    private String opertinstNm; // 운영기관명
    private String partclrMatr; // 특이사항
    private String imageNm; // 이미지

}
