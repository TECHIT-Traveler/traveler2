package com.ll.traveler.domain.review.review.entity;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.global.jpa.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    private String body; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;


}
