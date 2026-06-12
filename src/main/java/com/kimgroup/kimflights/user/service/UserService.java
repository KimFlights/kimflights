package com.kimgroup.kimflights.user.service;

import com.kimgroup.kimflights.user.dto.UserDTO;
import com.kimgroup.kimflights.user.repository.UserRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
            .map(user -> UserDTO.builder()
                .id(user.getId())
                .status(user.getStatus())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build())
            .toList();
    }
}
