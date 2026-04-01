package com.example.fooddeliverysystem.service;

import com.example.fooddeliverysystem.dto.MenuItemResponseDto;

import java.util.List;

public interface MenuItemService {
    MenuItemResponseDto getMenuItemById(Integer itemId);
    List<MenuItemResponseDto> getMenuItemsByRestaurantId(Integer restaurantId);
}
