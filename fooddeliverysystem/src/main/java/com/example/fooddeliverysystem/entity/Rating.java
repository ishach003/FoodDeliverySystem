package com.example.fooddeliverysystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DeliveryAddresses")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Rating {

    @Id
    @Column(name = "rating_id")
    private int ratingId;

    @Column(name = "rating_value")
    private int ratingValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
