package com.kimgroup.kimflights.user.service;

import java.util.Optional;

import com.kimgroup.kimflights.user.dto.AuthUserData;

public interface UserAuthService {
    Optional<AuthUserData> findByUsername(String username);
}