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
public class Ulsan extends Place {

    private String unqId; // 고유 아이디
    private String city; // 구군
    private String cls; // 휴무일
    private String remarks; // 비고
    private String zipCode; // 우편번호
    private String rgstDate; // 등록일자
}