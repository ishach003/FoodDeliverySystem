package com.example.fooddeliverysystem.service;


import com.example.fooddeliverysystem.dto.RatingDto;

import java.util.List;

public interface RatingService {
    RatingDto getRatingById(Integer ratingId);
    List<RatingDto> getRatingsByRestaurant(Integer restaurantId);

}
