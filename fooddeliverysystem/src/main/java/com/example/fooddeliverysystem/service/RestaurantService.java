package com.example.fooddeliverysystem.service;

import com.example.fooddeliverysystem.dto.RestaurantResponseDto;

public interface RestaurantService {
    RestaurantResponseDto getRestaurantById(Integer id);
}
