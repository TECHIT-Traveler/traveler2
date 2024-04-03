package com.ll.traveler.domain.review.review.controller;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.service.MemberService;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.place.place.service.PlaceService;
import com.ll.traveler.domain.review.review.dto.ReviewDTO;
import com.ll.traveler.domain.review.review.entity.Review;
import com.ll.traveler.domain.review.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/places/{placeId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberService memberService;
    private final PlaceService placeService;

    @PostMapping("")
    public ResponseEntity<?> createReview(
            @PathVariable("placeId") long placeId,
            @RequestBody ReviewDTO reviewDTO,
            Authentication authentication
    ) {
        // 장소 정보 가져오기
        Place place = placeService.getPlaceDataById(placeId);

        // 인증된 사용자 정보 가져오기
        Member member = memberService.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
        // 리뷰 생성
        Review review;
        try {
            review = reviewService.createReview(place, reviewDTO, member);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(review);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable("placeId") long placeId,
            @PathVariable("reviewId") long reviewId,
            @RequestBody ReviewDTO reviewDTO,
            Authentication authentication
    ) {
        // 리뷰 수정
        Review updatedReview = reviewService.updateReview(placeId, reviewId, reviewDTO, authentication);

        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable("placeId") long placeId,
            @PathVariable("reviewId") long reviewId,
            Authentication authentication
    ) {
        // 리뷰 삭제
        reviewService.deleteReview(placeId, reviewId, authentication);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Review>> getReviewsByPlaceId(
            @PathVariable("placeId") long placeId
    ) {
        // 장소별 리뷰 목록 조회
        List<Review> reviews = reviewService.getReviewsByPlaceId(placeId);

        return ResponseEntity.ok(reviews);
    }
}

