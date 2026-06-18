package com.kimgroup.kimflights.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRequestDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {}