package com.marci.todo.service;

import com.marci.todo.dto.UpdateAppUserRequest;
import com.marci.todo.exception.AppUserNotFoundException;
import com.marci.todo.model.AppUser;
import com.marci.todo.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public List<AppUser> getActiveAppUsers() {
        return appUserRepository.findByActiveTrue();
    }

    public List<AppUser> getInactiveAppUsers() {
        return appUserRepository.findByActiveFalse();
    }

    public AppUser getAppUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));
    }

    public AppUser updateAppUser(Long id, UpdateAppUserRequest request) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));

        appUser.setUsername(request.getUsername());
        appUser.setEmail(request.getEmail());
        appUser.setBirthDate(request.getBirthDate());

        return appUserRepository.save(appUser);
    }

    public void deleteById(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));

        appUser.setActive(false);
        appUser.setDeletedAt(LocalDateTime.now());

        appUserRepository.save(appUser);
    }
}