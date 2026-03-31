package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.config.CustomMapper;
import com.example.fooddeliverysystem.dto.CouponDto;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.CouponRepository;
import com.example.fooddeliverysystem.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public CouponDto getCouponById(Integer couponId) {
        log.info("Fetching coupon with id: {}", couponId);
        return couponRepository.findById(couponId)
                .map(CustomMapper::toCouponDto)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon", couponId));

    }
}
