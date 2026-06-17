package com.kimgroup.kimflights.security.authentication;

import com.kimgroup.kimflights.security.authentication.dto.LoginRequest;
import com.kimgroup.kimflights.security.authentication.dto.LoginResponse;
import com.kimgroup.kimflights.security.jwt.JwtService;
import com.kimgroup.kimflights.user.dto.UserRequestDTO;
import com.kimgroup.kimflights.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    // ---------------------------
    // REGISTER USER
    // ---------------------------
    @PostMapping("/registerUser")
    public String registerUser(@RequestBody UserRequestDTO request) {

        userService.registerUser(request);

        return "User registered";
    }

    // ---------------------------
    // REGISTER ADMIN
    // ---------------------------
    @PostMapping("/registerAdmin")
    public String registerAdmin(@RequestBody UserRequestDTO request) {

        userService.registerAdmin(request);

        return "Admin registered";
    }

    // ---------------------------
    // LOGIN
    // ---------------------------
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.username());

        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .username(userDetails.getUsername())
                .build();
    }
}