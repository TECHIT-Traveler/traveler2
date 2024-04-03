package com.ll.traveler.domain.review.review.repository;

import com.ll.traveler.domain.review.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaceId(long placeId);
}
