package com.kimgroup.kimflights.user.mapper;

import com.kimgroup.kimflights.user.dto.UserDTO;
import com.kimgroup.kimflights.user.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .status(user.getStatus())
                .role(user.getRole())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public User toEntity(UserDTO dto) {

        return User.builder()
                .id(dto.id())
                .status(dto.status())
                .role(dto.role())
                .username(dto.username())
                .password(dto.password())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();
    }
}