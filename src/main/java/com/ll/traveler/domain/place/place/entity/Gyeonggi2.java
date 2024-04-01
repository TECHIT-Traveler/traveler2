package com.ll.traveler.domain.place.place.entity;

import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Gyeonggi2 extends Place {
    private String location;
    private String time; // 이용시간
    private String homePage; // 홈페이지
}