package com.ll.traveler.domain.review.review.controller;

import com.ll.traveler.domain.review.review.dto.ReviewRequest;
import com.ll.traveler.domain.review.review.dto.ReviewResponse;
import com.ll.traveler.domain.review.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Long> createReview(@RequestBody ReviewRequest dto) {
        Long reviewId = reviewService.saveReview(dto);
        return ResponseEntity.ok(reviewId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long id) {
        ReviewResponse dto = reviewService.getReview(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long id, @RequestBody ReviewRequest dto) {
        ReviewResponse updatedDto = reviewService.updateReview(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }
}