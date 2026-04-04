package com.example.fooddeliverysystem.security;


import com.example.fooddeliverysystem.exception.InvalidTokenException;
import com.example.fooddeliverysystem.exception.TokenExpiredException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expiration;   // milliseconds — 86400000 = 24 hours

    // Build the HMAC-SHA256 signing key from the secret string
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ── EXTRACT ───────────────────────────────────────────────

    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // ── VALIDATE ──────────────────────────────────────────────

    public boolean validateToken(String token, String username) {
        String extracted = extractUsername(token);     // may throw
        return extracted.equals(username) && !isTokenExpired(token);
    }

    // ── INTERNAL ──────────────────────────────────────────────
    private boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException ex) {
            // Token was valid but has passed its expiry time
            throw new TokenExpiredException();

        } catch (MalformedJwtException ex) {
            // Token structure is broken (e.g. missing a "." section)
            throw new InvalidTokenException("malformed token structure");

        } catch (io.jsonwebtoken.security.SecurityException ex) {
            // Signature does not match — token was tampered with
            throw new InvalidTokenException("signature verification failed");

        } catch (UnsupportedJwtException ex) {
            // JWT uses an algorithm or format we do not support
            throw new InvalidTokenException("unsupported JWT format");

        } catch (IllegalArgumentException ex) {
            // Token string was null, empty, or whitespace only
            throw new InvalidTokenException("token string is empty or null");
        }
    }
}
