package com.example.uberreviewservice.adapters;

import com.example.uberreviewservice.dtos.ReviewResponseDTO;
import com.example.uberreviewservice.models.Review;

public interface ReviewToReviewResponseDTOAdapter {
    ReviewResponseDTO convertReviewToReviewResponseDTO(Review review);
}
