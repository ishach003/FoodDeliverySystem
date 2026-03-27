package com.example.fooddeliverysystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="Customers")
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    @Id
    @Column(name = "customer_id")
    private int customerId;

    @NotBlank
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Email
    @Column(name = "customer_email", nullable = false, unique = true)
    private String customerEmail;

    @Column(name = "customer_phone" ,unique = true)
    private String customerPhone;


}