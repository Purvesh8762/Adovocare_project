package com.legalaid.backend.controller;

import com.legalaid.backend.model.AuthResponse;
import com.legalaid.backend.model.LoginRequest;
import com.legalaid.backend.security.JwtUtil;
import com.legalaid.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // authenticate user
            var authToken = new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword());
            authenticationManager.authenticate(authToken);

            // generate tokens
            String accessToken = jwtUtil.generateAccessToken(request.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(request.getEmail());

            AuthResponse response = new AuthResponse(accessToken, refreshToken);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }

    // later you can add /register or /refresh endpoints here
}
