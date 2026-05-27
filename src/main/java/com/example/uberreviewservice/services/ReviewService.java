package com.example.uberreviewservice.services;

import com.example.uberreviewservice.dtos.CreateReviewDTO;
import com.example.uberreviewservice.dtos.UpdateReviewDTO;
import com.example.uberreviewservice.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    public Optional<Review> findReviewById(Long id);
    public List<Review> findAllReviews();
    public Review publishReview(CreateReviewDTO createReviewDTO);
    public Review updateReview(Long id, UpdateReviewDTO updateReviewDTO);
    public boolean deleteReviewById(Long id);
}
