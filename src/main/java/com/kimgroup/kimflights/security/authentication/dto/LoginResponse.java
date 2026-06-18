package com.kimgroup.kimflights.security.authentication.dto;

import com.kimgroup.kimflights.user.models.Role;


public record LoginResponse(
        String token,
        String username,
        Role role
) {}