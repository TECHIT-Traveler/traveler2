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
public class Place extends IdEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name; // 장소 이름
    private String address; // 주소
    private int latitude; // 위도
    private int longitude; // 경도
    private String number; // 전화번호
    private String area; // 지역
    private String homepage; // 홈페이지
    private String usageTime; // 영업 시간
    @OneToMany(mappedBy = "place", cascade = ALL, orphanRemoval = true) // 부모와의 관계가 끊긴 자식 엔티티는 자동으로 삭제
    @Builder.Default // 빌더 패턴을 사용하여 객체를 생성할 때 해당 필드의 기본값을 정의
    private List<Review> reviews = new ArrayList<>();
}
