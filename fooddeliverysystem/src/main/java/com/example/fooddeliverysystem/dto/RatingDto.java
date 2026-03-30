package com.example.fooddeliverysystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    private int    ratingId;
    private int    rating;
    private String review;
    private int    restaurantId;
    private int    orderId;
}