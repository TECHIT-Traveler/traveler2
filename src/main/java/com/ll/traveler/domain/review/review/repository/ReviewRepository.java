package com.ll.traveler.domain.review.review.repository;

import com.ll.traveler.domain.review.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
