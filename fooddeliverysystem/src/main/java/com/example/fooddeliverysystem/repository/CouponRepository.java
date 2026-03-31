package com.example.fooddeliverysystem.repository;

import com.example.fooddeliverysystem.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
}
