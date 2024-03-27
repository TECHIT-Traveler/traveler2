package com.ll.traveler.domain.place.place.gyeonggi2;

import com.ll.traveler.global.jpa.IdEntity;
import jakarta.persistence.Entity;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Gyeonggi2 extends IdEntity {

    private String location;
    private String address;
    private String contact;
    private String time; // 이용시간
    private String homePage; // 홈페이지

}