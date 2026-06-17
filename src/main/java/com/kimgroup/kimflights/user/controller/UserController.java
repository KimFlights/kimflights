package com.kimgroup.kimflights.user.controller;

import com.kimgroup.kimflights.user.dto.UserResponseDTO;
import com.kimgroup.kimflights.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ---------------------------
    // GET ALL USERS (ADMIN only)
    // ---------------------------
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDTO> getAllUsers() {

        return userService.getAllUsers();
    }

    // ---------------------------
    // GET USER BY ID (ADMIN only)
    // ---------------------------
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO getUserById(@PathVariable String id) {

        return userService.getUserById(id);
    }

    // ---------------------------
    // DELETE USER (ADMIN only)
    // ---------------------------
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable String id) {

        userService.deleteUser(id);
    }

    // ---------------------------
    // GET CURRENT LOGGED USER
    // ---------------------------
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public UserResponseDTO getMyProfile() {

        return userService.getCurrentUser();
    }
}