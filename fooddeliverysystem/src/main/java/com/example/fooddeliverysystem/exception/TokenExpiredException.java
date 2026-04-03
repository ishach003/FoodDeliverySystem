package com.example.fooddeliverysystem.exception;

public class TokenExpiredException extends UnauthorizedException {
    public TokenExpiredException() {
        super("JWT token has expired. Please login again at POST /auth/login.");
    }
}
