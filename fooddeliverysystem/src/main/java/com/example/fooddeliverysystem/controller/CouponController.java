package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.config.constants.CouponConstant;
import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/{couponId}")
    public ResponseEntity<ResponseDto> getCouponById(@PathVariable Integer couponId) {
        log.info("GET /api/v1/coupon/{}", couponId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CouponConstant.STATUS_200,
                        CouponConstant.MESSAGE_210,
                        couponService.getCouponById(couponId)));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDto> getActiveCoupons() {
        log.info("GET /api/v1/coupon/active");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CouponConstant.STATUS_200,
                        CouponConstant.MESSAGE_210,
                        couponService.getActiveCoupons()));
    }
}
