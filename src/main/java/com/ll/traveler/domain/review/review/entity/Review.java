package com.ll.traveler.domain.review.review.entity;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.review.photo.entity.Photo;
import com.ll.traveler.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)
public class Review extends BaseEntity {
    @ManyToOne
    private Place place;

    @ManyToOne
    private Member member;

    private String content;

    private int rating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
