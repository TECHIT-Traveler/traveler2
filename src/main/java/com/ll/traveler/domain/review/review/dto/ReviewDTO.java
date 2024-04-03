package com.ll.traveler.domain.review.review.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReviewDTO {

    private String content;
    private int rating;
    private MultipartFile photoFile;

}
