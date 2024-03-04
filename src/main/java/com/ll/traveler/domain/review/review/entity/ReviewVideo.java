package com.ll.traveler.domain.review.review.entity;

import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
public class ReviewVideo extends IdEntity {
    private String path; // 파일 경로
    @ManyToOne
    private Review review;
}
