package com.example.uberreviewservice.adapters;

import com.example.uberreviewservice.dtos.CreateReviewDTO;
import com.example.uberreviewservice.models.Review;

public interface CreateReviewDTOToReviewAdapter {
    public Review convertCreateReviewDTOToReview(CreateReviewDTO createReviewDTO);
}
