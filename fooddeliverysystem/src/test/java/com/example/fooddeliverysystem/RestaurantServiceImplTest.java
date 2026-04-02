package com.example.fooddeliverysystem;

import com.example.fooddeliverysystem.entity.Restaurant;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.RestaurantRepository;
import com.example.fooddeliverysystem.service.impl.RestaurantServiceImpl;
import com.example.fooddeliverysystem.dto.RestaurantResponseDto;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RestaurantServiceImpl — API #3")
class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setRestaurantName("Tasty Bites");
        restaurant.setRestaurantAddress("123 Main St");
        restaurant.setRestaurantPhone("+1234567890");
    }
    @Nested
    @DisplayName("getRestaurantById()")
    class GetRestaurantById {

        @Test
        @DisplayName("POSITIVE — returns RestaurantResponseDTO when found")
        void positive_returnsRestaurantDTOWhenFound() {

            when(restaurantRepository.findById(1))
                    .thenReturn(Optional.of(restaurant));

            RestaurantResponseDto result =
                    restaurantService.getRestaurantById(1);

            assertThat(result).isNotNull();
            assertThat(result.getRestaurantId()).isEqualTo(1);
            assertThat(result.getRestaurantName()).isEqualTo("Tasty Bites");
            assertThat(result.getRestaurantAddress()).isEqualTo("123 Main St");
            assertThat(result.getRestaurantPhone()).isEqualTo("+1234567890");

            verify(restaurantRepository, times(1)).findById(1);
        }

//        @Test
//        @DisplayName("NEGATIVE — throws exception when not found")
//        void negative_throwsExceptionWhenNotFound() {
//
//            when(restaurantRepository.findById(99))
//                    .thenReturn(Optional.empty());
//
//            assertThatThrownBy(() ->
//                    restaurantService.getRestaurantById(99))
//                    .isInstanceOf(ResourceNotFoundException.class);
//
//            verify(restaurantRepository, times(1)).findById(99);
//        }
//    }
}}