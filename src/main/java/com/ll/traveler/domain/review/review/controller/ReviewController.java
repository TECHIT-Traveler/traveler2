package com.ll.traveler.domain.review.review.controller;

import com.ll.traveler.domain.member.member.entity.Member;

import com.ll.traveler.domain.member.member.service.MemberService;
import com.ll.traveler.domain.review.review.dto.ReviewDTO;
import com.ll.traveler.domain.review.review.entity.Review;
import com.ll.traveler.domain.review.review.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO reviewDTO) {
        Member author = memberService.findById(reviewDTO.getAuthor().getId());

        Review review = convertToEntity(reviewDTO, author);
        Review createdReview = reviewService.createReview(review);

        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    private Review convertToEntity(ReviewDTO reviewDTO, Member author) {
        Review review = new Review();
        review.setBody(reviewDTO.getBody());
        review.setAuthor(author);
        return review;
    }

    // 모든 리뷰 조회 API
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
