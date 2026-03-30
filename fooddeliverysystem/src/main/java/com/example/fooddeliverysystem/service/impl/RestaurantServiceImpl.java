package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.config.CustomMapper;
import com.example.fooddeliverysystem.dto.RestaurantResponseDto;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.RestaurantRepository;
import com.example.fooddeliverysystem.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public RestaurantResponseDto getRestaurantById(Integer id) {
        log.info("Fetching restaurant with id: {}", id);
        return restaurantRepository.findById(id)
                .map(CustomMapper::toRestaurantDto)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", id));
    }
}