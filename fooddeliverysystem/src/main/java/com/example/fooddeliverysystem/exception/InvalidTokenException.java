package com.example.fooddeliverysystem.exception;

public class InvalidTokenException extends UnauthorizedException {
    public InvalidTokenException(String detail) {
        super("Invalid JWT token: " + detail);
    }
}
git add src/main/java/com/example/fooddeliverysystem/exception/InvalidTokenException.java