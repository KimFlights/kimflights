package com.kimgroup.kimflights.user.dto;

import com.kimgroup.kimflights.user.models.Role;
import com.kimgroup.kimflights.user.models.StatusEnum;
import jakarta.validation.constraints.NotBlank;

public record AuthUserData(
        @NotBlank
        String username,
        @NotBlank
        String password,
        Role role,
        StatusEnum status
) {
}