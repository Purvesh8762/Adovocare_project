package com.legalaid.backend.controller;

import com.legalaid.backend.model.AuthResponse;
import com.legalaid.backend.model.LoginRequest;
import com.legalaid.backend.model.RegisterRequest;
import com.legalaid.backend.model.UserDto;
import com.legalaid.backend.security.JwtUtil;
import com.legalaid.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

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

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshToken) {

        // extract username from refresh token
        String username = jwtUtil.extractUsername(refreshToken);

        // create a new access token
        String newAccessToken = jwtUtil.generateAccessToken(username);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
    }

    /**
     * Registration endpoint (Task 23)
     * - Validates and delegates to UserService.register
     * - Ensures password encoding and role assignment are done in service layer
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // attempt registration
        boolean created = userService.register(request);

        if (created) {
            // return created (201)
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            // user already exists or could not create
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with this email already exists");
        }
    }
}
