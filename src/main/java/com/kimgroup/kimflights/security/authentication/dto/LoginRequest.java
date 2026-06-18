package com.kimgroup.kimflights.security.authentication.dto;

public record LoginRequest(
        String username,
        String password
) {}
