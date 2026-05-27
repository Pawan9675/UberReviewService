package com.example.uberreviewservice.services;

import com.example.uberreviewservice.adapters.CreateReviewDTOToReviewAdapter;
import com.example.uberreviewservice.dtos.CreateReviewDTO;
import com.example.uberreviewservice.dtos.UpdateReviewDTO;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CreateReviewDTOToReviewAdapter createReviewDTOToReviewAdapter;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CreateReviewDTOToReviewAdapter createReviewDTOToReviewAdapter) {
        this.reviewRepository = reviewRepository;
        this.createReviewDTOToReviewAdapter = createReviewDTOToReviewAdapter;
    }

    @Override
    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review publishReview(CreateReviewDTO createReviewDTO) {
        Review review = createReviewDTOToReviewAdapter.convertCreateReviewDTOToReview(createReviewDTO);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, UpdateReviewDTO updateReviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));

        if(updateReviewDTO.getRating() != null) {
            review.setRating(updateReviewDTO.getRating());
        }

        if(updateReviewDTO.getContent() != null) {
            review.setContent(updateReviewDTO.getContent());
        }
        return reviewRepository.save(review);
    }

    @Override
    public boolean deleteReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));

        reviewRepository.delete(review);
        return true;
    }
}
