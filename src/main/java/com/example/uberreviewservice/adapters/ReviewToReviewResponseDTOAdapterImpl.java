package com.example.uberreviewservice.adapters;

import com.example.uberreviewservice.dtos.ReviewResponseDTO;
import com.example.uberreviewservice.models.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewToReviewResponseDTOAdapterImpl implements ReviewToReviewResponseDTOAdapter{

    @Override
    public ReviewResponseDTO convertReviewToReviewResponseDTO(Review review) {
        ReviewResponseDTO reviewResponseDTO = ReviewResponseDTO.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .bookingId(review.getBooking().getId())
                .build();
        return reviewResponseDTO;
    }
}
