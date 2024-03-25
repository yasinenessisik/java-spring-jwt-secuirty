package com.javaspringjwt.javaspringjwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    @Column(unique = true)
    private String username;

    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String email;

    private String phoneNumber;
}
