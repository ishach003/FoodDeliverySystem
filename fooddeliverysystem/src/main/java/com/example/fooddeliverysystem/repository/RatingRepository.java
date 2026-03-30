package com.example.fooddeliverysystem.repository;

import com.example.fooddeliverysystem.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
