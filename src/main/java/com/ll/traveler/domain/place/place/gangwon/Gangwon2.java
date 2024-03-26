package com.ll.traveler.domain.place.place.gangwon;

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
public class Gangwon2 extends IdEntity {

    private String 업체구분;
    private String 업체명;
    private String 지번_주소;
    private String 도로명_주소;
    private String 위도;
    private String 경도;
    private String 연락처;

} //엔티티