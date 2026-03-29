package com.example.fooddeliverysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDto {
    private int    restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhone;
}
