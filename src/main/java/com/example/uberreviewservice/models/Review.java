package com.example.uberreviewservice.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking_review")
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends BaseModel{

    @Column(nullable = false)
    private String content;

    private Double rating;

    @OneToOne
    @JoinColumn(nullable = false)
    private Booking booking; // we have defined a 1:1 relationship between Review and Booking

    @Override
    public String toString() {
        return "Review{" +
                "content='" + content + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                '}';
    }
}
