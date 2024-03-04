package com.ll.traveler.domain.place.place.entity;

import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
public class PlacePhoto extends IdEntity {
    private String path; // 사진 경로
    @ManyToOne
    private Place place;
}
