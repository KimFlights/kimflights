package com.kimgroup.kimflights.user.models;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return status == StatusEnum.ACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == StatusEnum.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status == StatusEnum.ACTIVE;
    }

    @Override
    public boolean isEnabled() {
        return status == StatusEnum.ACTIVE;
    }
}