package com.ll.traveler.domain.place.place.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
}



