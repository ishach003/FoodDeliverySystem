package com.example.fooddeliverysystem.repository;

import com.example.fooddeliverysystem.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    @Query("SELECT c FROM Coupon c WHERE c.expiryDate >= CURRENT_DATE ORDER BY c.expiryDate ASC")
    List<Coupon> findActiveCoupons();
}
