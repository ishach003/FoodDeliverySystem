package com.example.fooddeliverysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequestDto {
    private String     itemName;
    private String     itemDescription;
    private BigDecimal itemPrice;
    private Integer    restaurantId;
}