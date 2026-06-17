package com.kimgroup.kimflights.security.authentication.principal;

import com.kimgroup.kimflights.user.models.Role;
import com.kimgroup.kimflights.user.models.StatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class CustomUserPrincipal implements UserDetails {

    private final String username;
    private final String password;
    private final Role role;
    private final StatusEnum status;

    public CustomUserPrincipal(String username,
                               String password,
                               Role role,
                               StatusEnum status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    // OPTIONAL: extra domain info
    public Role getRole() {
        return role;
    }
}