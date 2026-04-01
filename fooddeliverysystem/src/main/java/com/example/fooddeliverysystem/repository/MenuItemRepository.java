package com.example.fooddeliverysystem.repository;

import com.example.fooddeliverysystem.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByRestaurant_RestaurantId(Integer restaurantId);
}