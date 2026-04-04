package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.entity.LoginRequest;
import com.example.fooddeliverysystem.entity.LoginResponse;
import com.example.fooddeliverysystem.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserDetailsService    userDetailsService;
    private final JwtUtil jwtUtil;

    // ── POST /auth/login ──────────────────────────────────────
    /**
     * Authenticate with username + password.
     * Returns a JWT token valid for 24 hours.
     *
     * Request body:
     *   { "username": "admin", "password": "password" }
     *
     * Success response (200):
     *   {
     *     "status": 200,
     *     "message": "Login successful",
     *     "data": {
     *       "token": "eyJhbGci...",
     *       "username": "admin",
     *       "role": "ROLE_ADMIN"
     *     }
     *   }
     *
     * Failure response (401):
     *   { "status": 401, "error": "Unauthorized",
     *     "message": "Invalid username or password.", "path": "/auth/login" }
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequest request) {
        log.info("Login attempt for user: {}", request.getUsername());

        // authenticate() throws BadCredentialsException on wrong credentials.
        // GlobalExceptionHandler catches it and returns 401.
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        // Credentials are correct — load user to get role
        UserDetails user  = userDetailsService.loadUserByUsername(request.getUsername());
        String      token = jwtUtil.generateToken(user.getUsername());
        String      role  = user.getAuthorities().iterator().next().getAuthority();

        log.info("Login successful for user: {}", request.getUsername());

        return ResponseEntity.ok(
                new ResponseDto(200, "Login successful",
                        new LoginResponse(token, user.getUsername(), role))
        );
    }

}