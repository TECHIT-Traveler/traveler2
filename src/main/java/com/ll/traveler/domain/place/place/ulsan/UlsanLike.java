package com.ll.traveler.domain.place.place.ulsan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.place.place.gyeonggi2.Gyeonggi2;
import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
public class UlsanLike extends IdEntity {
    @ManyToOne
    private Member member;

    @ManyToOne
    @JsonBackReference
    private Ulsan post;

}
