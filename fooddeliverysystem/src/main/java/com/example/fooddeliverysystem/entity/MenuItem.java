package com.example.fooddeliverysystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "MenuItems")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MenuItem {

    @Id
    @Column(name = "item_id")
    private int itemId;

    @NotBlank
    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @NotNull
    @Column(name = "item_price", nullable = false)
    private BigDecimal itemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}