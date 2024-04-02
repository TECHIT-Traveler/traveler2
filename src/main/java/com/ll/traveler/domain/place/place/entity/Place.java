package com.ll.traveler.domain.place.place.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ll.traveler.domain.member.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
//@MappedSuperclass
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Place {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name; // 이름
    private String address; // 주소
    private String contact; // 연락처
    private String latitude; // 위도
    private String longitude; // 경도

    @OneToMany(mappedBy = "place", cascade =  ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<PlaceLike> likes = new ArrayList<>();

    public boolean hasLike(Member member) {
        return likes.stream()
                .anyMatch(like -> like.getMember().equals(member));
    }

    public void like(Member member) {
        likes.add(
                PlaceLike.builder()
                        .member(member)
                        .place(this)
                        .build()
        );
    }

    public void cancelLike(Member member) {
        likes.removeIf(like -> like.getMember().equals(member));
    }
}



