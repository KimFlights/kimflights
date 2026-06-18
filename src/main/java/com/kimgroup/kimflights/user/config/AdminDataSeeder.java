package com.kimgroup.kimflights.user.config;

import com.kimgroup.kimflights.user.models.Role;
import com.kimgroup.kimflights.user.models.StatusEnum;
import com.kimgroup.kimflights.user.models.User;
import com.kimgroup.kimflights.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminDataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User(
                    "admin",
                    passwordEncoder.encode("admin123"),
                    StatusEnum.ACTIVE,
                    Role.ROLE_ADMIN
            );

            userRepository.save(admin);

            System.out.println("✅ Admin user successfully seeded. Username: admin | Password: admin123");
        }
    }
}
