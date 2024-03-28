package com.ll.traveler.domain.review.review.service;

import com.ll.traveler.domain.member.member.entity.Member;
import com.ll.traveler.domain.member.member.repository.MemberRepository;
import com.ll.traveler.domain.review.review.entity.Review;
import com.ll.traveler.domain.review.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    @Transactional // 트랜잭션을 사용하여 영속성 컨텍스트를 관리합니다.
    public void saveReviewWithAuthor(Review review, Member member) {
        // Member 엔티티를 저장합니다.
        memberRepository.save(member);
        review.setAuthor(member);
        reviewRepository.save(review);
    }
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
