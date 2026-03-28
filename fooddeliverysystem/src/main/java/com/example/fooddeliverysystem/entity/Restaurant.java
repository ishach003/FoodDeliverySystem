package com.example.fooddeliverysystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Restaurant {
    @Id
    @Column(name = "restaurant_id")
    private int restaurantId;

    @NotBlank
    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @NotBlank
    @Column(name = "restaurant_address", nullable = false)
    private String restaurantAddress;

    @Column(name = "restaurant_phone")
    private String restaurantPhone;
}
