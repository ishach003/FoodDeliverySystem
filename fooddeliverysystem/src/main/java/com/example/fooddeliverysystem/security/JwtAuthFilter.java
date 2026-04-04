package com.example.fooddeliverysystem.security;


import com.example.fooddeliverysystem.exception.ErrorResponse;
import com.example.fooddeliverysystem.exception.InvalidTokenException;
import com.example.fooddeliverysystem.exception.TokenExpiredException;
import com.example.fooddeliverysystem.exception.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // ObjectMapper is used to write JSON error responses from the filter layer
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // ── STEP 1: No Authorization header or wrong format ───
        // Pass through to next filter — JwtAuthEntryPoint will
        // return 401 if the endpoint requires authentication.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ── STEP 2: Extract token (everything after "Bearer ") ─
        String token = authHeader.substring(7);

        try {
            // ── STEP 3: Extract username from token ───────────
            // Throws TokenExpiredException or InvalidTokenException
            String username = jwtUtil.extractUsername(token);

            // ── STEP 4: Load user and set SecurityContext ──────
            // Only do this if username was found AND no auth is
            // already set (prevents processing the same request twice)
            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);

                // Validate token matches the loaded user
                if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                    // Build Spring authentication token with user's roles
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    // Attach request metadata (IP, session ID etc.)
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));

                    // Tell Spring Security this request is authenticated
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // ── STEP 5: Continue to controller ────────────────
            filterChain.doFilter(request, response);

        } catch (TokenExpiredException ex) {
            // Token was present but has expired
            writeErrorJson(response, request, 401, "Token Expired", ex.getMessage());

        } catch (InvalidTokenException ex) {
            // Token was present but malformed or tampered
            writeErrorJson(response, request, 401, "Invalid Token", ex.getMessage());

        } catch (UnauthorizedException ex) {
            // Any other auth-related problem
            writeErrorJson(response, request, 401, "Unauthorized", ex.getMessage());
        }
    }
    private void writeErrorJson(HttpServletResponse response,
                                HttpServletRequest  request,
                                int    status,
                                String error,
                                String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(
                mapper.writeValueAsString(
                        new ErrorResponse(status, error, message, request.getRequestURI())
                )
        );
    }
}