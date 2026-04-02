package com.example.fooddeliverysystem;

import com.example.fooddeliverysystem.dto.RatingDto;
import com.example.fooddeliverysystem.entity.Order;
import com.example.fooddeliverysystem.entity.Rating;
import com.example.fooddeliverysystem.entity.Restaurant;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.RatingRepository;
import com.example.fooddeliverysystem.repository.RestaurantRepository;
import com.example.fooddeliverysystem.service.impl.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    private Rating rating;
    private Restaurant restaurant;
    private Order order;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setRestaurantId(1);

        order = new Order();
        order.setOrderId(10);

        rating = new Rating();
        rating.setRatingId(1);
        rating.setRestaurant(restaurant);
        rating.setOrder(order);
    }


    // getRatingById()
    @Test
    void getRatingById_positive() {
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        RatingDto result = ratingService.getRatingById(1);

        assertThat(result).isNotNull();

        verify(ratingRepository).findById(1);
    }

    @Test
    void getRatingById_negative() {
        when(ratingRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ratingService.getRatingById(99))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(ratingRepository).findById(99);
    }
    // getRatingsByRestaurant()
    @Test
    void getRatingsByRestaurant_positive() {
        when(restaurantRepository.findById(1))
                .thenReturn(Optional.of(restaurant));

        when(ratingRepository.findByRestaurant_RestaurantId(1))
                .thenReturn(List.of(rating));

        List<RatingDto> result = ratingService.getRatingsByRestaurant(1);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);

        verify(restaurantRepository).findById(1);
        verify(ratingRepository).findByRestaurant_RestaurantId(1);
    }

    @Test
    void getRatingsByRestaurant_negative() {
        when(restaurantRepository.findById(99))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> ratingService.getRatingsByRestaurant(99))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(ratingRepository, never())
                .findByRestaurant_RestaurantId(any());
    }
}