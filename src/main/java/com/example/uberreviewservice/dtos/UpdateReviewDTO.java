package com.example.uberreviewservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReviewDTO {
    private String content;
    private Double rating;
}
