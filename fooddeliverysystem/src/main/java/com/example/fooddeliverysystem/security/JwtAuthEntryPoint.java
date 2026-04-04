package com.example.fooddeliverysystem.security;



import com.example.fooddeliverysystem.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    /**
     * Triggered when an unauthenticated user tries to access a protected URL.
     * This is NOT triggered when a token is present but expired/invalid —
     * that is caught in JwtAuthFilter above.
     */
    @Override
    public void commence(HttpServletRequest  request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ErrorResponse body = new ErrorResponse(
                401,
                "Unauthorized",
                "No token provided. Login at POST /auth/login and send the token as: "
                        + "Authorization: Bearer <token>",
                request.getRequestURI()
        );

        response.getWriter().write(mapper.writeValueAsString(body));
    }
}

