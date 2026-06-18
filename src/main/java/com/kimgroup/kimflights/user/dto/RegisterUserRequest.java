package com.kimgroup.kimflights.user.dto;

public record RegisterUserRequest(
        String username,
        String password,
        String firstName,
        String lastName
) {}