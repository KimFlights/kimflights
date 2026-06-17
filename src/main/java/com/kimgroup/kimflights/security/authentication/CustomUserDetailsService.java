package com.kimgroup.kimflights.security.authentication;

import com.kimgroup.kimflights.user.dto.AuthUserData;
import com.kimgroup.kimflights.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        AuthUserData user = userAuthService
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.username(),
                user.password(),
                user.status().name().equals("ACTIVE"),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority(user.role().name()))
        );
    }
}