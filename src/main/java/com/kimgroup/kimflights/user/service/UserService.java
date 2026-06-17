package com.kimgroup.kimflights.user.service;

import com.kimgroup.kimflights.user.dto.UserRequestDTO;
import com.kimgroup.kimflights.user.dto.UserResponseDTO;
import com.kimgroup.kimflights.user.mapper.UserMapper;
import com.kimgroup.kimflights.user.models.Role;
import com.kimgroup.kimflights.user.models.StatusEnum;
import com.kimgroup.kimflights.user.models.User;
import com.kimgroup.kimflights.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.NamedInterface;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@NamedInterface
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponseDTO getUserById(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return userMapper.toResponse(user);
    }

    public UserResponseDTO registerUser(UserRequestDTO dto) {

        User user = User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.ROLE_USER)
                .status(StatusEnum.ACTIVE)
                .build();

        return userMapper.toResponse(
                userRepository.save(user)
        );
    }

    public UserResponseDTO registerAdmin(UserRequestDTO dto) {

        User user = User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.ROLE_ADMIN)
                .status(StatusEnum.ACTIVE)
                .build();

        return userMapper.toResponse(
                userRepository.save(user)
        );
    }

    public void deleteUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    public UserResponseDTO getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return userMapper.toResponse(user);
    }
}