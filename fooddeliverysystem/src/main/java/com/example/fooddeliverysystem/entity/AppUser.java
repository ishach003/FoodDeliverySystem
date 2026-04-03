package com.example.fooddeliverysystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;   // always stored BCrypt-encoded, NEVER plain text

    @Column(nullable = false)
    private String role;       // "ROLE_ADMIN" or "ROLE_USER"
}