package com.ll.traveler.domain.review.review.controller;

import com.ll.traveler.domain.review.review.dto.ReviewRequest;
import com.ll.traveler.domain.review.review.entity.Review;
import com.ll.traveler.domain.review.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest) {
        // ReviewRequest DTO를 Review 엔티티로 변환
        Review review = convertToEntity(reviewRequest);
        Review createdReview = reviewService.createReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    private Review convertToEntity(ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setBody(reviewRequest.getBody());
        // 이하 필요한 변환 로직 구현, author 및 place 설정 등
        return review;
    }

    // 기타 엔드포인트들...
}