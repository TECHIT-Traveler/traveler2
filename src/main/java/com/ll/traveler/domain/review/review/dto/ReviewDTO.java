package com.ll.traveler.domain.review.review.dto;

import com.ll.traveler.domain.member.member.entity.Member;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDTO {
    private Long id;
    private String body;
    private Member author;
    private String nickname; // 닉네임 필드 추가
    private int scope;
}
