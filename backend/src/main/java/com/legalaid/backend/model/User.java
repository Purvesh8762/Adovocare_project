package com.legalaid.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password; // store hashed password

    // Single role as varchar (CITIZEN, LAWYER, NGO, ADMIN)
    @Column(nullable = false, length = 50)
    private String role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // One-to-one with Profile (Profile owns relationship via user_id)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Profile profile;

    public void setProfile(Profile profile) {
        if (profile == null) {
            if (this.profile != null) {
                this.profile.setUser(null);
            }
            this.profile = null;
        } else {
            profile.setUser(this);
            this.profile = profile;
        }
    }
}

