package com.kimgroup.kimflights.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.user.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
