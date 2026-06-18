package com.kimgroup.kimflights.user.dto;

import com.kimgroup.kimflights.user.models.Role;
import com.kimgroup.kimflights.user.models.StatusEnum;

public record AuthUserData(
        String username,
        String password,
        Role role,
        StatusEnum status
) {
}