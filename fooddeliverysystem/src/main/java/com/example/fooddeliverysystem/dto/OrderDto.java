package com.example.fooddeliverysystem.dto;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Integer orderId;
    private Integer customerId;
    private Integer restaurantId;

    private LocalDateTime orderDate;
    private String status;
}
