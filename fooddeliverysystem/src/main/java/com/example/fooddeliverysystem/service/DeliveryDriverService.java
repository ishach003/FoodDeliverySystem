package com.example.fooddeliverysystem.service;


import com.example.fooddeliverysystem.dto.DeliveryDriverDto;

public interface DeliveryDriverService {
    DeliveryDriverDto getDriverById(Integer driverId);
}