package com.example.fooddeliverysystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Coupon {
    @Id
    @Column(name = "coupon_id")
    private int couponId;

    @Column(name = "coupon_code", unique = true)
    private String couponCode;

    @Column(name = "discount_amount")
    private double discountAmount;

    @Column(name = "expiry_date")
    private LocalDate expiry_date;
}