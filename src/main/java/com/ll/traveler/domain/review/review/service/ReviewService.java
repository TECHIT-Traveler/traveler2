package com.ll.traveler.domain.review.review.service;

import com.ll.traveler.domain.member.member.repository.MemberRepository;
import com.ll.traveler.domain.place.place.repository.PlaceRepository;
import com.ll.traveler.domain.review.review.dto.ReviewRequest;
import com.ll.traveler.domain.review.review.dto.ReviewResponse;
import com.ll.traveler.domain.review.review.entity.Review;
import com.ll.traveler.domain.review.review.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public Long saveReview(ReviewRequest dto) {
        Review review = dto.toEntity();
        review = reviewRepository.save(review);
        return review.getId();
    }

    public ReviewResponse getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + id));
        return new ReviewResponse(review);
    }

    @Transactional
    public ReviewResponse updateReview(Long id, ReviewRequest dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        review.setBody(dto.getBody());
        review.setScope(dto.getScope());
        reviewRepository.save(review);
        return new ReviewResponse(review);
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));
        reviewRepository.delete(review);
    }
}