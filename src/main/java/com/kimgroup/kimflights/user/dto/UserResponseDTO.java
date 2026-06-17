package com.kimgroup.kimflights.user.dto;

import com.kimgroup.kimflights.user.models.Role;
import com.kimgroup.kimflights.user.models.StatusEnum;

public record UserResponseDTO(
        String id,
        String username,
        Role role,
        StatusEnum status
) {}