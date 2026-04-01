package com.example.fooddeliverysystem.service;

import com.example.fooddeliverysystem.dto.CouponDto;

import java.util.List;

public interface CouponService {
    CouponDto getCouponById(Integer couponId);
    List<CouponDto> getActiveCoupons();
}
