package com.ll.traveler.domain.place.place.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDTO {

    private String Type; // 업체구분
    private String PlaceNm; // 업체명
    private String landAddress; // 지번주소
    private String loadAddress; // 도로명주소
    private int latitude; // 위도
    private int longitude; // 경도
    private String phoneNumber; // 전화번호
    
}
