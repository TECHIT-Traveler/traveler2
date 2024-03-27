package com.ll.traveler.domain.review.review.dto;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.review.review.entity.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewResponse {
    private Long id;
    private Member author;
    private String body;
    private int scope;
    private String likes;
    private Boolean delete;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.body = review.getBody();
        this.scope = review.getScope();
    }
}

