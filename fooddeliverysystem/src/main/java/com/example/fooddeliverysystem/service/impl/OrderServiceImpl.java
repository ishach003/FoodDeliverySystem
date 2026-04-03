package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.dto.OrderDto;
import com.example.fooddeliverysystem.entity.Order;
import com.example.fooddeliverysystem.repository.CustomerRepository;
import com.example.fooddeliverysystem.repository.OrderRepository;
import com.example.fooddeliverysystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<OrderDto> getOrdersByCustomerId(Integer customerId) {
        log.info("Fetching orders for customer id: {}", customerId);

        // ✅ DO NOT THROW EXCEPTION (important fix)
        if (!customerRepository.existsById(customerId)) {
            return List.of();   // return empty → UI handles it
        }

        List<Order> orders = orderRepository.findByCustomer_CustomerId(customerId);

        if (orders.isEmpty()) return List.of();

        List<OrderDto> result = new ArrayList<>();

        for (Order order : orders) {

            OrderDto dto = new OrderDto();

            // ✅ FLAT MAPPING
            dto.setOrderId(order.getOrderId());
            dto.setOrderDate(order.getOrderDate());
            dto.setStatus(order.getOrderStatus());

            if (order.getCustomer() != null) {
                dto.setCustomerId(order.getCustomer().getCustomerId());
            }

            if (order.getRestaurant() != null) {
                dto.setRestaurantId(order.getRestaurant().getRestaurantId());
            }

            result.add(dto);
        }

        return result;
    }

    @Override
    public OrderDto getOrderDetailsById(Integer orderId) {
        log.info("Fetching order details for order id: {}", orderId);

        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) return null;   // frontend handles safely

        OrderDto dto = new OrderDto();

        dto.setOrderId(order.getOrderId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getOrderStatus());

        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getCustomerId());
        }

        if (order.getRestaurant() != null) {
            dto.setRestaurantId(order.getRestaurant().getRestaurantId());
        }

        return dto;
    }
}