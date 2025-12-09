package com.legalaid.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "expertise", columnDefinition = "TEXT")
    private String expertise;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "verified")
    private Boolean verified = false;

    @Column(name = "organization", length = 200)
    private String organization;

    @Column(name = "contact_info", columnDefinition = "TEXT")
    private String contactInfo;

    // 🔥 Custom setter required by User.setProfile()
    public void setUser(User user) {
        this.user = user;
    }
}
