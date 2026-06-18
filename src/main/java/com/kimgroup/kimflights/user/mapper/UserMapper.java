package com.kimgroup.kimflights.user.mapper;

import com.kimgroup.kimflights.user.dto.UserResponseDTO;
import com.kimgroup.kimflights.user.models.User;

import org.springframework.stereotype.Component;

import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class UserMapper {
    PasswordEncoder passwordEncoder;

    public UserResponseDTO toResponse(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getStatus()
        );
    }
}