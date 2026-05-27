package com.example.uberreviewservice.controllers;

import com.example.uberreviewservice.adapters.ReviewToReviewResponseDTOAdapter;
import com.example.uberreviewservice.dtos.CreateReviewDTO;
import com.example.uberreviewservice.dtos.ReviewResponseDTO;
import com.example.uberreviewservice.dtos.UpdateReviewDTO;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.services.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewToReviewResponseDTOAdapter reviewToReviewResponseDTOAdapter;

    @Autowired
    public ReviewController(ReviewService reviewService, ReviewToReviewResponseDTOAdapter reviewToReviewResponseDTOAdapter) {
        this.reviewService = reviewService;
        this.reviewToReviewResponseDTOAdapter = reviewToReviewResponseDTOAdapter;
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
        Optional<Review> review = reviewService.findReviewById(reviewId);

        if (review.isPresent()) {
            ReviewResponseDTO response = reviewToReviewResponseDTOAdapter.convertReviewToReviewResponseDTO(review.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found with id: " + reviewId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> findAllReviews() {
        List<Review> reviews = reviewService.findAllReviews();

        List<ReviewResponseDTO> response = new ArrayList<>();

        for (Review review : reviews) {
            ReviewResponseDTO reviewResponseDTO = reviewToReviewResponseDTOAdapter.convertReviewToReviewResponseDTO(review);
            response.add(reviewResponseDTO);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDTO request) {
        try {
            Review review = reviewService.publishReview(request);
            ReviewResponseDTO response = reviewToReviewResponseDTOAdapter.convertReviewToReviewResponseDTO(review);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewDTO request) {
        try {
            Review review = reviewService.updateReview(reviewId, request);

            ReviewResponseDTO response = reviewToReviewResponseDTOAdapter.convertReviewToReviewResponseDTO(review);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        try {
            reviewService.deleteReviewById(reviewId);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
