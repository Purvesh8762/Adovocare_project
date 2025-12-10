package com.legalaid.backend.config;

import com.legalaid.backend.model.User;
import com.legalaid.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadTestUser(UserRepository userRepository,
                                          PasswordEncoder passwordEncoder) {
        return args -> {
            String email = "test@example.com";

            // Only create if not present
            if (userRepository.findByEmail(email).isEmpty()) {
                User user = new User();
                user.setEmail(email);
                user.setUsername("testuser"); // NOT NULL in DB
                user.setPassword(passwordEncoder.encode("password123"));
                user.setRole("CITIZEN");      // or your enum/string value
                user.setCreatedAt(LocalDateTime.now()); // if column not nullable

                userRepository.save(user);
                System.out.println("Created test user: " + email + " / password123");
            } else {
                System.out.println("Test user already exists: " + email);
            }
        };
    }
}
