package com.kimgroup.kimflights.user;

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
