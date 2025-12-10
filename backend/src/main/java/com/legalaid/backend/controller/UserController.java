package com.legalaid.backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/profile")
    public String getUserProfile(Authentication authentication) {

        // authentication.getName() returns email/username of logged-in user
        String loggedInUser = authentication.getName();

        return "Your profile data for: " + loggedInUser;
    }
}
