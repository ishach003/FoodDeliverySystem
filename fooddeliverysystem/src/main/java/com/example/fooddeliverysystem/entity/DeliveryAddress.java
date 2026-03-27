package com.example.fooddeliverysystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "DeliveryAddresses")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryAddress {
    @Id
    @Column(name = "address_id")
    private int addressId;

    @Column(name = "address_line")
    private String addressLine;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}