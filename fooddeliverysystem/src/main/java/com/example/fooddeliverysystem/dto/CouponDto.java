package com.example.fooddeliverysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
        private int couponId;
        private String  couponCode;
        private BigDecimal discountAmount;
        private LocalDate expiryDate;
}
