package com.example.uberreviewservice.repositories;

import com.example.uberreviewservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    Integer countAllByRatingIsLessThanEqual(double rating);
    Integer countAllByRatingIsGreaterThanEqual(double rating);

    List<Review> findAllByRatingIsLessThanEqual(double rating);
    List<Review> findAllByRatingIsGreaterThanEqual(double rating);

    List<Review> findAllByCreatedAtBefore(Date createdAt);
    List<Review> findAllByCreatedAtAfter(Date createdAt);
    List<Review> findAllByCreatedAtBetween(Date createdAtStart, Date createdAtEnd);

    @Query("SELECT r FROM Review r WHERE r.booking.id = :bookingId")
    Review findReviewByBookingId(@Param("bookingId") Long bookingId);
}
