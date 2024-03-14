package com.ll.traveler.domain.place.place.entity;

import com.ll.traveler.domain.review.review.entity.Review;
import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Place extends IdEntity {

    private String areaName; // 지역 명

    private String partName; // 분야 명
    private String title; // 업체 명
    private String keyword; // 검색 키워드
    private String  address; // 주소
    private int latitude; // 위도
    private int longitude; // 경도
    private String tel; // 전화번호
    private String usedTime; // 이용시간
    private String content; // 소개
    private String mainFacility; // 주요시설
    private String usedCost; // 이용요금
    private String policyCautions; // 애견정책
    private String image; // 이미지 상세

    @OneToMany(mappedBy = "place", cascade = ALL, orphanRemoval = true) // 부모와의 관계가 끊긴 자식 엔티티는 자동으로 삭제
    @Builder.Default // 빌더 패턴을 사용하여 객체를 생성할 때 해당 필드의 기본값을 정의
    private List<Review> reviews = new ArrayList<>();


}
