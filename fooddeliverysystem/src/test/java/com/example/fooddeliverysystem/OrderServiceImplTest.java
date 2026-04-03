package com.example.fooddeliverysystem;

import com.example.fooddeliverysystem.dto.OrderDto;
import com.example.fooddeliverysystem.dto.OrderItemDetailDto;
import com.example.fooddeliverysystem.entity.*;
import com.example.fooddeliverysystem.entity.Order;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.CustomerRepository;
import com.example.fooddeliverysystem.repository.OrderRepository;
import com.example.fooddeliverysystem.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OrderServiceImpl")
class OrderServiceImplTest {

    @Mock private OrderRepository    orderRepository;
    @Mock private CustomerRepository customerRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Customer  customer;
    private Restaurant  restaurant;
    private DeliveryDriver driver;
    private Order  order;
    private OrderItemDetailDto itemDetail;

    private static final LocalDateTime ORDER_DATE =
            LocalDateTime.of(2024, 1, 1, 12, 0, 0);

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("John Smith");
        customer.setCustomerEmail("john@example.com");
        customer.setCustomerPhone("+1234567890");

        restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setRestaurantName("Tasty Bites");
        restaurant.setRestaurantAddress("123 Main St");
        restaurant.setRestaurantPhone("+1234567890");

        driver = new DeliveryDriver();
        driver.setDriverId(1);
        driver.setDriverName("Michael Johnson");
        driver.setDriverPhone("+11234567890");
        driver.setDriverVehicle("Car");

        order = new Order();
        order.setOrderId(1);
        order.setOrderDate(ORDER_DATE);
        order.setOrderStatus("Pending");
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setDeliveryDriver(driver);

        itemDetail = new OrderItemDetailDto(
                ORDER_DATE,
                "Pending",
                2,
                "Pizza",
                "Delicious pizza with assorted toppings",
                new BigDecimal("12.99"),
                "Tasty Bites",
                "123 Main St",
                "+1234567890"
        );
    }

    @Test
    @DisplayName("POSITIVE TestCase when OrderId is Exist")
    void positive_returnsOrderDtoWithItemsWhenFound() {
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        when(orderRepository.getOrderDetailsByOrderId(1))
                .thenReturn(List.of(itemDetail));

        OrderDto result = orderService.getOrderDetailsById(1);

        assertThat(result).isNotNull();
        assertThat(result.getOrderStatus()).isEqualTo("Pending");
        assertThat(result.getOrderDate()).isEqualTo(ORDER_DATE);
        assertThat(result.getCustomer().getCustomerName()).isEqualTo("John Smith");
        assertThat(result.getRestaurant().getRestaurantName()).isEqualTo("Tasty Bites");
        assertThat(result.getDeliveryDriver().getDriverVehicle()).isEqualTo("Car");
        assertThat(result.getOrderItems()).hasSize(1);
        assertThat(result.getOrderItems().get(0).getItemName()).isEqualTo("Pizza");
        assertThat(result.getOrderItems().get(0).getQuantity()).isEqualTo(2);
        assertThat(result.getOrderItems().get(0).getItemPrice())
                .isEqualByComparingTo("12.99");

        verify(orderRepository, times(1)).findById(1);
        verify(orderRepository, times(1)).getOrderDetailsByOrderId(1);
    }

    @Test
    @DisplayName("NEGATIVE TestCase when OrderId is missing")
    void negative_throwsResourceNotFoundWhenOrderMissing() {
        when(orderRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.getOrderDetailsById(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Order")
                .hasMessageContaining("99");

        verify(orderRepository, times(1)).findById(99);
        verify(orderRepository, never()).getOrderDetailsByOrderId(any());
    }

    @Test
    @DisplayName("POSITIVE TestCase when customerId exists")
    void positive_returnsOrderListWhenCustomerExists() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepository.getOrderDetailsByCustomerId(1))
                .thenReturn(List.of(itemDetail));

        List<OrderDto> result = orderService.getOrdersByCustomerId(1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getOrderStatus()).isEqualTo("Pending");
        assertThat(result.get(0).getOrderDate()).isEqualTo(ORDER_DATE);
        assertThat(result.get(0).getOrderItems()).hasSize(1);
        assertThat(result.get(0).getOrderItems().get(0).getItemName()).isEqualTo("Pizza");
        assertThat(result.get(0).getOrderItems().get(0).getQuantity()).isEqualTo(2);
        assertThat(result.get(0).getOrderItems().get(0).getRestaurantName())
                .isEqualTo("Tasty Bites");

        verify(customerRepository, times(1)).findById(1);
        verify(orderRepository, times(1)).getOrderDetailsByCustomerId(1);
    }

    @Test
    @DisplayName("NEGATIVE TestCase:-When customerId Missing")
    void negative_throwsResourceNotFoundWhenCustomerMissing() {
        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.getOrdersByCustomerId(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Customer")
                .hasMessageContaining("99");

        verify(customerRepository, times(1)).findById(99);
        verify(orderRepository, never()).getOrderDetailsByCustomerId(any());
    }
}