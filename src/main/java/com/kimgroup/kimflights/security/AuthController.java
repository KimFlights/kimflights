package com.kimgroup.kimflights.security;

import com.kimgroup.kimflights.security.dto.LoginRequest;
import com.kimgroup.kimflights.security.jwt.JwtService;
import com.kimgroup.kimflights.user.dto.UserDTO;
import com.kimgroup.kimflights.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;


    @PostMapping("/registerAdmin")
//    @PreAuthorize("hasRole('ADMIN')")
    public String registerAdmin(@RequestBody UserDTO user) {
        userService.registerAdmin(user);
        return "ADMIN registered";
    }

    @PostMapping("/registerUser")
//    @PreAuthorize("hasRole('USER')")
    public String registerUser(@RequestBody UserDTO user) {
        userService.registerUser(user);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        return jwtService.generateToken(userDetails);


    }
}