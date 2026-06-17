package com.kimgroup.kimflights.user.controller;



import com.kimgroup.kimflights.user.dto.UserDTO;
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
    public List<UserDTO> getAllUsers() {

        return userService.getAllUsers();
    }

    // ---------------------------
    // GET USER BY ID (ADMIN only)
    // ---------------------------
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO getUserById(@PathVariable String id) {

        return userService.getUserById(id);
    }
//
//    // ---------------------------
//    // CREATE USER (ADMIN only)
//    // ---------------------------
//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public UserDTO createUser(@RequestBody UserDTO dto) {
//
//        return userService.createUser(dto);
//    }

    // ---------------------------
    // UPDATE USER (ADMIN only)
    // ---------------------------
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO updateUser(
            @PathVariable String id,
            @RequestBody UserDTO dto) {

        return userService.updateUser(id, dto);
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
    @GetMapping("/me/")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public UserDTO getMyProfile() {

        return userService.getCurrentUser();
    }
}
