package com.kimgroup.kimflights.user.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    public User(String username, String password, StatusEnum status, Role role) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    private Role role;
}