package com.example.uberreviewservice.adapters;

import com.example.uberreviewservice.dtos.CreateReviewDTO;
import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewDTOToReviewAdapterImpl implements CreateReviewDTOToReviewAdapter {

    private final BookingRepository bookingRepository;

    public CreateReviewDTOToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review convertCreateReviewDTOToReview(CreateReviewDTO createReviewDTO) {
        if (createReviewDTO.getBookingId() == null) {
            throw new IllegalArgumentException("bookingId is required");
        }

        Optional<Booking> booking = bookingRepository.findById(createReviewDTO.getBookingId());

        if (booking.isEmpty()) {
            throw new EntityNotFoundException("Booking not found with id: " + createReviewDTO.getBookingId());
        }

        Review review = Review.builder()
                .content(createReviewDTO.getContent())
                .rating(createReviewDTO.getRating())
                .booking(booking.get())
                .build();

        return review;
    }
}
