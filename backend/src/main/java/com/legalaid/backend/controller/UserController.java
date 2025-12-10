package com.legalaid.backend.controller;

import com.legalaid.backend.model.User;
import com.legalaid.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET /users/me  -> return currently authenticated user
    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();  // from JWT subject
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }

    // Simple DTO for updating profile
    public record UpdateProfileRequest(String username) {}

    // PUT /users/me  -> update own profile (here only username)
    @PutMapping("/me")
    public User updateProfile(@RequestBody UpdateProfileRequest request,
                              Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        user.setUsername(request.username());
        return userRepository.save(user);
    }
}
