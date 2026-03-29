package com.example.fooddeliverysystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriverDTO {
    private int    driverId;
    private String driverName;
    private String driverPhone;
    private String driverVehicle;
}

