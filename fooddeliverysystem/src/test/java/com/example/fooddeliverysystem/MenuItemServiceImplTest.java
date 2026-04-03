package com.example.fooddeliverysystem;
import com.example.fooddeliverysystem.dto.MenuItemResponseDto;
import com.example.fooddeliverysystem.entity.MenuItem;
import com.example.fooddeliverysystem.entity.Restaurant;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.MenuItemRepository;
import com.example.fooddeliverysystem.repository.RestaurantRepository;
import com.example.fooddeliverysystem.service.impl.MenuItemServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MenuItemServiceImpl Test")
class MenuItemServiceImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    private Restaurant restaurant;
    private MenuItem pizza;
    private MenuItem burger;

    @BeforeEach
    void setUp() {

        restaurant = new Restaurant();
        restaurant.setRestaurantId(1);

        pizza = new MenuItem();
        pizza.setItemId(1);
        pizza.setItemName("Pizza");
        pizza.setItemPrice(new BigDecimal("12.99"));
        pizza.setRestaurant(restaurant);

        burger = new MenuItem();
        burger.setItemId(2);
        burger.setItemName("Burger");
        burger.setItemPrice(new BigDecimal("9.99"));
        burger.setRestaurant(restaurant);
    }
    @Test
    @DisplayName("POSITIVE — returns menu items list")
    void positive_returnsMenuItems() {

        when(restaurantRepository.findById(1))
                .thenReturn(Optional.of(restaurant));

        when(menuItemRepository.findByRestaurant_RestaurantId(1))
                .thenReturn(List.of(pizza, burger));

        List<MenuItemResponseDto> result =
                menuItemService.getMenuItemsByRestaurantId(1);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);

        verify(restaurantRepository).findById(1);
        verify(menuItemRepository).findByRestaurant_RestaurantId(1);
    }
    @Test
    @DisplayName("NEGATIVE — restaurant not found")
    void negative_restaurantNotFound() {

        when(restaurantRepository.findById(99))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                menuItemService.getMenuItemsByRestaurantId(99))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(menuItemRepository, never())
                .findByRestaurant_RestaurantId(anyInt());
    }
    // For getMenuItemById()
    @Test
    @DisplayName("POSITIVE — returns menu item")
    void positive_returnsMenuItem() {

        when(menuItemRepository.findById(1))
                .thenReturn(Optional.of(pizza));

        MenuItemResponseDto result =
                menuItemService.getMenuItemById(1);

        assertThat(result).isNotNull();
        assertThat(result.getItemId()).isEqualTo(1);

        verify(menuItemRepository).findById(1);
    }

}