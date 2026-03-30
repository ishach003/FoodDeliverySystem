package com.example.fooddeliverysystem.service;

import com.example.fooddeliverysystem.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrdersByCustomerId(Integer customerId);
    OrderDto getOrderDetailsById(Integer orderId);
}
