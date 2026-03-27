package com.example.fooddeliverysystem.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DeliveryDrivers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryDriver {
    @Id
    @Column(name = "driver_id")
    private int driverId;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_phone")
    private String driverPhone;
}