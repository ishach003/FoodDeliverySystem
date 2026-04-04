package com.example.fooddeliverysystem;

import com.example.fooddeliverysystem.dto.OrderDto;
import com.example.fooddeliverysystem.entity.*;
import com.example.fooddeliverysystem.repository.CustomerRepository;
import com.example.fooddeliverysystem.repository.OrderRepository;
import com.example.fooddeliverysystem.service.impl.OrderServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Customer customer;
    private Restaurant restaurant;
    private DeliveryDriver driver;
    private Order order;

    private static final LocalDateTime ORDER_DATE =
            LocalDateTime.of(2024, 1, 1, 12, 0, 0);

    @BeforeEach
    void setUp() {

        customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("John Smith");

        restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setRestaurantName("Tasty Bites");

        driver = new DeliveryDriver();
        driver.setDriverId(1);
        driver.setDriverVehicle("Car");

        order = new Order();
        order.setOrderId(1);
        order.setOrderDate(ORDER_DATE);
        order.setOrderStatus("Pending");
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setDeliveryDriver(driver);
    }

    @Test
    @DisplayName("POSITIVE: Order exists")
    void positive_returnsOrderDtoWhenFound() {

        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        OrderDto result = orderService.getOrderDetailsById(1);

        assertThat(result).isNotNull();
        assertThat(result.getOrderId()).isEqualTo(1);
        assertThat(result.getCustomerId()).isEqualTo(1);
        assertThat(result.getRestaurantId()).isEqualTo(1);
        assertThat(result.getStatus()).isEqualTo("Pending");
        assertThat(result.getOrderDate()).isEqualTo(ORDER_DATE);

        verify(orderRepository, times(1)).findById(1);
    }


    @Test
    @DisplayName("NEGATIVE: Order does not exist")
    void negative_returnsNullWhenOrderMissing() {

        when(orderRepository.findById(99)).thenReturn(Optional.empty());

        OrderDto result = orderService.getOrderDetailsById(99);

        // 👉 Service does NOT throw exception → returns null
        assertThat(result).isNull();

        verify(orderRepository, times(1)).findById(99);
    }


    @Test
    @DisplayName("POSITIVE: Customer exists")
    void positive_returnsOrderListWhenCustomerExists() {

        when(customerRepository.existsById(1)).thenReturn(true);
        when(orderRepository.findByCustomer_CustomerId(1))
                .thenReturn(List.of(order));

        List<OrderDto> result = orderService.getOrdersByCustomerId(1);

        assertThat(result).hasSize(1);

        OrderDto dto = result.get(0);

        assertThat(dto.getOrderId()).isEqualTo(1);
        assertThat(dto.getCustomerId()).isEqualTo(1);
        assertThat(dto.getRestaurantId()).isEqualTo(1);
        assertThat(dto.getStatus()).isEqualTo("Pending");
        assertThat(dto.getOrderDate()).isEqualTo(ORDER_DATE);

        verify(customerRepository, times(1)).existsById(1);
        verify(orderRepository, times(1)).findByCustomer_CustomerId(1);
    }


    @Test
    @DisplayName("NEGATIVE: Customer does not exist")
    void negative_returnsEmptyListWhenCustomerMissing() {

        when(customerRepository.existsById(99)).thenReturn(false);

        List<OrderDto> result = orderService.getOrdersByCustomerId(99);

        assertThat(result).isEmpty();

        verify(customerRepository, times(1)).existsById(99);
        verify(orderRepository, never()).findByCustomer_CustomerId(any());
    }
}