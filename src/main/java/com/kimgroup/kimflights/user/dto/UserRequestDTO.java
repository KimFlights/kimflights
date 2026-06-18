package com.kimgroup.kimflights.user.dto;

import lombok.Builder;

@Builder
public record UserRequestDTO(
        String username,
        String password
) {}