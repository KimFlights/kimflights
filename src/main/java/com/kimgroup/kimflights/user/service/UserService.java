package com.kimgroup.kimflights.user.service;




import com.kimgroup.kimflights.user.dto.UserDTO;
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

    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDTO getUserById(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return userMapper.toDto(user);
    }

    // ---------------------------
    // USER SELF REGISTRATION
    // ---------------------------
    public UserDTO registerUser(UserDTO dto) {

        User user = userMapper.toEntity(dto);

        user.setRole(Role.ROLE_USER);
        user.setStatus(StatusEnum.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    // ---------------------------
    // ADMIN CREATES ADMIN USER
    // ---------------------------
    public UserDTO registerAdmin(UserDTO dto) {

        User user = userMapper.toEntity(dto);

        user.setRole(Role.ROLE_ADMIN);
        user.setStatus(StatusEnum.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }


//    public UserDTO createUser(UserDTO dto) {
//
//        User user = userMapper.toEntity(dto);
//
//        User savedUser =
//                userRepository.save(user);
//
//        return userMapper.toDto(savedUser);
//    }

    public UserDTO updateUser(
            String id,
            UserDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setStatus(dto.status());
        user.setRole(dto.role());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());

        User updatedUser =
                userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }



    public void deleteUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    public UserDTO getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return userMapper.toDto(user);
    }
}