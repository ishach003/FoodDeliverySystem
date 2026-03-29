package com.example.fooddeliverysystem.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequestDTO {
    private String     itemName;
    private String     itemDescription;
    private BigDecimal itemPrice;
    private Integer    restaurantId;
}
