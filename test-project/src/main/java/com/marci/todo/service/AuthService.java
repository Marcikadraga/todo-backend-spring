package com.marci.todo.service;

import com.marci.todo.dto.LoginRequest;
import com.marci.todo.dto.RegisterRequest;
import com.marci.todo.model.AppUser;
import com.marci.todo.model.AppUserRole;
import com.marci.todo.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser register(RegisterRequest request) {
        AppUser appUser = AppUser.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .birthDate(request.getBirthDate())
                .active(true)
                .role(AppUserRole.USER)
                .createdAt(LocalDateTime.now())
                .deletedAt(null)
                .build();

        return appUserRepository.save(appUser);
    }

    public AppUser login(LoginRequest request) {
        AppUser appUser = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                appUser.getPasswordHash()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid email or password");
        }

        return appUser;
    }
}