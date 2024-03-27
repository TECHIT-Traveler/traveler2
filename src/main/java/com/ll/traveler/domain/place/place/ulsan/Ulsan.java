package com.ll.traveler.domain.place.place.ulsan;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.place.place.gyeonggi2.Gyeonggi2Like;
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
public class Ulsan extends IdEntity {

    private String unqId; // 고유 아이디
    private String city; // 구군
    private String facility; // 업체명
    //private String lcnsInfo; // 인허가정보
    //private String lcnsAprDate; // 인허가일자
    //private String lcnsCncDate; // 인허가취소일자
    private String bsnsStts; // 영업상태명
    //private String failDate; // 폐업일자
    private String tel; // 전화번호
    //private String wkdBsn; // 평일영업시간
    //private String wknBsn; // 주말영업시간
    private String cls; // 휴무일
    private String remarks; // 비고
    private String zipCode; // 우편번호
    private String streetNameAddress; // 도로명주소
    private String address; // 지번주소
    private String lat;  // 위도
    private String lng; // 경도
    private String rgstDate; // 등록일자

    @OneToMany(mappedBy = "post", cascade =  ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<UlsanLike> likes = new ArrayList<>();

    public boolean hasLike(Member member) {
        return likes.stream()
                .anyMatch(like -> like.getMember().equals(member));
    }

    public void like(Member member) {
        likes.add(
                UlsanLike.builder()
                        .member(member)
                        .post(this)
                        .build()
        );
    }

    public void cancelLike(Member member) {
        likes.removeIf(like -> like.getMember().equals(member));
    }
}