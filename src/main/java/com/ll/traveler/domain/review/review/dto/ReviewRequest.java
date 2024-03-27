package com.ll.traveler.domain.review.review.dto;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.review.review.entity.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ReviewRequest {
    private Long id;
    private String body;
    private int scope;
    private Member author;
    private Place place;
    public Review toEntity() {
        return Review.builder()
                .id(id)
                .body(body)
                .scope(scope)
                .author(author)
                .place(place)
                .build();
    }
}