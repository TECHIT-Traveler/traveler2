package com.ll.traveler.domain.place.place.gyeonggi2;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Gyeonggi2 extends IdEntity {

    private String 지역명; // 등록일자
    private String 주소; // 등록일자
    private String 전화번호; // 등록일자
    private String 이용시간; // 등록일자
    private String 홈페이지; // 등록일자
    @OneToMany(mappedBy = "post", cascade =  ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<Gyeonggi2Like> likes = new ArrayList<>();

    public boolean hasLike(Member member) {
        return likes.stream()
                .anyMatch(like -> like.getMember().equals(member));
    }

    public void like(Member member) {
        likes.add(
                Gyeonggi2Like.builder()
                        .member(member)
                        .post(this)
                        .build()
        );
    }

    public void cancelLike(Member member) {
        likes.removeIf(like -> like.getMember().equals(member));
    }

}