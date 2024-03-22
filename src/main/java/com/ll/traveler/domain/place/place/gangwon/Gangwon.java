package com.ll.traveler.domain.place.place.gangwon;

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
public class Gangwon extends IdEntity {
//    private String latitude; // 위도
//    private String longitude; // 경도
    private String roadAddress; // 도로명 주소
    private String businessType; // 업종
    private String businessName; // 업체명
    private String contactNumber; // 전화번호
    private String jibunAddress; // 지번 주소
}
