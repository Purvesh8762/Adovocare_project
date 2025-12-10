package com.legalaid.backend.controller;

import com.legalaid.backend.model.AuthResponse;
import com.legalaid.backend.model.LoginRequest;
import com.legalaid.backend.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        // authenticate username + password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // generate tokens
        String accessToken = jwtUtil.generateAccessToken(request.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(request.getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(@RequestBody String refreshToken) {

        // extract username from refresh token
        String username = jwtUtil.extractUsername(refreshToken);

        // create a new access token
        String newAccessToken = jwtUtil.generateAccessToken(username);

        return new AuthResponse(newAccessToken, refreshToken);
    }
}
