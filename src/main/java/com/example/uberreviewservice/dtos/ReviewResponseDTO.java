package com.example.uberreviewservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewResponseDTO {
    private Long id;
    private String content;
    private Double rating;
    private Long bookingId;
}
