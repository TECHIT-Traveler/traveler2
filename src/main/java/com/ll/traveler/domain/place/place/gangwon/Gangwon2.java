package com.ll.traveler.domain.place.place.gangwon;

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
public class Gangwon2 extends IdEntity { // 엔티티

    private String division;
    private String name;
    private String lotAddress;
    private String streetAddress;
    private String latitude;
    private String longitude;
    private String contact;

    @OneToMany(mappedBy = "post", cascade =  ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<GangwonLike> likes = new ArrayList<>();

    public boolean hasLike(Member member) {
        return likes.stream()
                .anyMatch(like -> like.getMember().equals(member));
    }

    public void like(Member member) {
        likes.add(
                GangwonLike.builder()
                        .member(member)
                        .post(this)
                        .build()
        );
    }

    public void cancelLike(Member member) {
        likes.removeIf(like -> like.getMember().equals(member));
    }
}
