package com.kimgroup.kimflights.user.mapper;

import com.kimgroup.kimflights.user.dto.UserRequestDTO;
import com.kimgroup.kimflights.user.dto.UserResponseDTO;
import com.kimgroup.kimflights.user.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponse(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getStatus()
        );
    }

    public User toEntity(UserRequestDTO dto) {

        return User.builder()
                .username(dto.username())
                .password(dto.password())
                .build();
    }
}