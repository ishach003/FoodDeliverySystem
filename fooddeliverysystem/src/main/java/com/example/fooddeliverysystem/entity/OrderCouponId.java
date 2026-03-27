package com.example.fooddeliverysystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class OrderCouponId implements Serializable {

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "coupon_id")
    private Integer couponId;
}
