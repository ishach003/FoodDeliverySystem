package com.example.fooddeliverysystem.repository;


import com.example.fooddeliverysystem.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    // Used by CustomUserDetailsService.loadUserByUsername()
    Optional<AppUser> findByUsername(String username);

    // Used by AuthController to prevent duplicate usernames at register
    boolean existsByUsername(String username);
}
