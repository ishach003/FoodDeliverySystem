package com.example.fooddeliverysystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Rating {

    @Id
    @Column(name = "rating_id")
    private int ratingId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review")
    private String review;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
