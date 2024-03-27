package com.ll.traveler.domain.review.review.entity;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

@Entity
@SuperBuilder
@AllArgsConstructor(access = PROTECTED) // 클래스의 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성
@NoArgsConstructor(access = PUBLIC) // 파라미터가 없는 디폴트 생성자를 자동으로 생성
@Getter
@Setter
@ToString(callSuper = true)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body; // 내용

    private int scope; // 별점

    @ManyToOne
    private Member author; // 작성자

    @ManyToOne
    private Place place; // 여행 장소

    @OneToMany(mappedBy = "review", cascade = ALL, orphanRemoval = true)

    @Builder.Default
    private List<ReviewLike> likes = new ArrayList<>();
}
