package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.config.CustomMapper;
import com.example.fooddeliverysystem.dto.MenuItemResponseDto;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.MenuItemRepository;
import com.example.fooddeliverysystem.repository.RestaurantRepository;
import com.example.fooddeliverysystem.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;


    @Override
    public MenuItemResponseDto getMenuItemById(Integer itemId) {

        log.info("Fetching menu item with id: {}", itemId);

        return menuItemRepository.findById(itemId)
                .map(CustomMapper::toMenuItemDto)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem", itemId));
    }
    @Override
    public List<MenuItemResponseDto> getMenuItemsByRestaurantId(Integer restaurantId) {

        log.info("Fetching menu items for restaurant id: {}", restaurantId);

        // check restaurant exists
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", restaurantId));

        return menuItemRepository.findByRestaurant_RestaurantId(restaurantId)
                .stream()
                .map(CustomMapper::toMenuItemDto)
                .toList();
    }
}