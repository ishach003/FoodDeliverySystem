package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.config.CustomMapper;
import com.example.fooddeliverysystem.dto.RatingDto;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.RatingRepository;
import com.example.fooddeliverysystem.repository.RestaurantRepository;
import com.example.fooddeliverysystem.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public RatingDto getRatingById(Integer ratingId) {
        log.info("Fetching rating with id: {}", ratingId);
        return ratingRepository.findById(ratingId)
                .map(CustomMapper::toRatingDto)
                .orElseThrow(() -> new ResourceNotFoundException("Rating", ratingId));
    }
}