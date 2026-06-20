package com.kimgroup.kimflights.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimgroup.kimflights.user.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUsername(String username);
}
