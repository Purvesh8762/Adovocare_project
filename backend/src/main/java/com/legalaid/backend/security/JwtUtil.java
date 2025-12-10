package com.legalaid.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    // TODO: Implement real JWT generation and validation once library/version is finalized.

    public String generateToken(String username) {
        // temporary dummy token, just for structure
        return "DUMMY_TOKEN_FOR_" + username;
    }

    public boolean isTokenValid(String token, String username) {
        return token.equals("DUMMY_TOKEN_FOR_" + username);
    }
}
