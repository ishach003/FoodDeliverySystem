package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.dto.OrderDto;
import com.example.fooddeliverysystem.dto.OrderItemDetailDto;
import com.example.fooddeliverysystem.entity.Order;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.CustomerRepository;
import com.example.fooddeliverysystem.repository.OrderRepository;
import com.example.fooddeliverysystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        // Validate customer exists
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));

        List<OrderItemDetailDto> details =
                orderRepository.getOrderDetailsByCustomerId(customerId);

        // Group all items into one OrderDTO per call (all have same customer)
        if (details.isEmpty()) return List.of();

        OrderDto dto = new OrderDto();
        dto.setOrderStatus(details.get(0).getOrderStatus());
        dto.setOrderDate(details.get(0).getOrderDate());
        dto.setOrderItems(details);
        return List.of(dto);
    }
    @Override
    public OrderDto getOrderDetailsById(Integer orderId) {
        log.info("Fetching order details for order id: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        List<OrderItemDetailDto> orderDetails =
                orderRepository.getOrderDetailsByOrderId(orderId);

        OrderDto orderDTO = new OrderDto();
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setCustomer(order.getCustomer());
        orderDTO.setRestaurant(order.getRestaurant());
        orderDTO.setDeliveryDriver(order.getDeliveryDriver());
        orderDTO.setOrderItems(orderDetails);
        return orderDTO;
    }

}
