package com.kimgroup.kimflights.user.service;

import com.kimgroup.kimflights.user.dto.AuthUserData;
import com.kimgroup.kimflights.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository repository;

    @Override
    public Optional<AuthUserData> findByUsername(String username) {

        return repository.findByUsername(username)
                .map(user -> new AuthUserData(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRole(),
                        user.getStatus()
                ));
    }
}