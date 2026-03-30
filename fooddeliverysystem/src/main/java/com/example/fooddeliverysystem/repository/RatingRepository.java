package com.example.fooddeliverysystem.repository;

import com.example.fooddeliverysystem.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByRestaurant_RestaurantId(Integer restaurantId);
}
