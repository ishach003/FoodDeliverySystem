package com.example.fooddeliverysystem.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDetailDto {
    private LocalDateTime orderDate;
    private String        orderStatus;
    private int           quantity;
    private String        itemName;
    private String        itemDescription;
    private BigDecimal    itemPrice;
    private String        restaurantName;
    private String        restaurantAddress;
    private String        restaurantPhone;
}
