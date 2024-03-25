package com.ll.traveler.domain.review.review.controller;

import com.ll.traveler.domain.place.place.gangwon.Gangwon2ApiService;
import com.ll.traveler.domain.review.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final Gangwon2ApiService gangwon2ApiService;
    private final ReviewRepository reviewRepository;
}
