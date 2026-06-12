package com.kimgroup.kimflights.user.dto;

import com.kimgroup.kimflights.user.models.StatusEnum;

import lombok.Builder;

@Builder
public record UserDTO(
    String id,
    StatusEnum status,
    String username,
    String password,
    String firstName,
    String lastName
) {}
