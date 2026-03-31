package com.example.fooddeliverysystem.repository;

import com.example.fooddeliverysystem.entity.DeliveryDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Integer> {
    List<DeliveryDriver> findByDriverVehicle(String vehicle);
}
