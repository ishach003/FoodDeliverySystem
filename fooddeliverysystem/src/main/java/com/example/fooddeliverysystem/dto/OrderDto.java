package com.example.fooddeliverysystem.dto;


import com.example.fooddeliverysystem.entity.Customer;
import com.example.fooddeliverysystem.entity.DeliveryDriver;
import com.example.fooddeliverysystem.entity.Restaurant;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private LocalDateTime        orderDate;
    private String               orderStatus;
    private Customer customer;
    private Restaurant restaurant;
    private DeliveryDriver deliveryDriver;
    private List<OrderItemDetailDto> orderItems;
}