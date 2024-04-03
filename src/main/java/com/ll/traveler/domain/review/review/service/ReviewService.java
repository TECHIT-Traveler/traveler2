package com.ll.traveler.domain.review.review.service;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.repository.MemberRepository;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.place.place.repository.PlaceRepository;
import com.ll.traveler.domain.review.photo.entity.Photo;
import com.ll.traveler.domain.review.review.dto.ReviewDTO;
import com.ll.traveler.domain.review.review.entity.Review;
import com.ll.traveler.domain.review.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Review createReview(Place place, ReviewDTO reviewDto, Member member) throws IOException {

        Review review = Review.builder()
                .place(place)
                .member(member)
                .content(reviewDto.getContent())
                .rating(reviewDto.getRating())
                .build();

        return reviewRepository.save(review);
    }

    // 리뷰 수정
    @Transactional
    public Review updateReview(long placeId, long reviewId, ReviewDTO reviewDTO, Authentication authentication) {
        // 리뷰를 수정할 장소와 리뷰 ID로 리뷰를 가져옵니다.
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found with id: " + placeId));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

        // 현재 인증된 사용자의 정보를 가져옵니다.
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member currentUser = memberRepository.findByUsername(userDetails.getUsername());


        // 리뷰의 내용과 평점을 업데이트합니다.
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());

        return reviewRepository.save(review);
    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(long placeId, long reviewId, Authentication authentication) {
        // 리뷰를 삭제할 장소와 리뷰 ID로 리뷰를 가져옵니다.
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found with id: " + placeId));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

        // 현재 인증된 사용자의 정보를 가져옵니다.
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member currentUser = memberRepository.findByUsername(userDetails.getUsername());

        // 현재 사용자가 리뷰 작성자인지 확인합니다.
        if (!review.getMember().equals(currentUser)) {
            throw new RuntimeException("You are not authorized to delete this review.");
        }

        // 리뷰를 삭제합니다.
        reviewRepository.delete(review);
    }

    public List<Review> getReviewsByPlaceId(long placeId) {
        return reviewRepository.findByPlaceId(placeId);
    }
}
