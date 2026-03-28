package com.example.fooddeliverysystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_coupons")

public class OrderCoupon {
    @EmbeddedId
    private OrderCouponId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("couponId")
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
}
