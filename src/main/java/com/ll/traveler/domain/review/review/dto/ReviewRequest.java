package com.ll.traveler.domain.review.review.dto;

import com.ll.traveler.domain.member.member.entity.Member;
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
    private Member author;
}
