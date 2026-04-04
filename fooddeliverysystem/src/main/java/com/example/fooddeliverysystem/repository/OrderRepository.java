package com.example.fooddeliverysystem.repository;

import com.example.fooddeliverysystem.dto.OrderItemDetailDto;
import com.example.fooddeliverysystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Find all Order entities for a given customer
    List<Order> findByCustomer_CustomerId(Integer customerId);
    @Query("""
    SELECT new com.example.fooddeliverysystem.dto.OrderItemDetailDto(
        o.orderDate,
        o.orderStatus,
        io.quantity,
        mi.itemName,
        mi.itemDescription,
        mi.itemPrice,
        r.restaurantName,
        r.restaurantAddress,
        r.restaurantPhone
    )
    FROM OrderItem io
    JOIN io.order o
    JOIN io.menuItem mi
    JOIN mi.restaurant r
    WHERE o.customer.customerId = :customerId
""")
    List<OrderItemDetailDto> getOrderDetailsByCustomerId(@Param("customerId") Integer customerId);
    @Query("""
    SELECT new com.example.fooddeliverysystem.dto.OrderItemDetailDto(
        o.orderDate,
        o.orderStatus,
        io.quantity,
        mi.itemName,
        mi.itemDescription,
        mi.itemPrice,
        r.restaurantName,
        r.restaurantAddress,
        r.restaurantPhone
    )
    FROM OrderItem io
    JOIN io.order o
    JOIN io.menuItem mi
    JOIN mi.restaurant r
    WHERE o.orderId = :orderId
""")
    List<OrderItemDetailDto> getOrderDetailsByOrderId(@Param("orderId") Integer orderId);

}