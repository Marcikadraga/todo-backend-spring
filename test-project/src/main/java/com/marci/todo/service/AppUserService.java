package com.marci.todo.service;

import org.springframework.stereotype.Service;
import com.marci.todo.repository.AppUserRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    private final AppUserRepository appUserRepository;

    public TodoService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllAppUser(){
        return appUserRepository.findAll();
    }

    public AppUser getAppUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));
    }

    public AppUser createAppUser(CreateTodoRequest request) {
        AppUser appUser = AppUser.builder()
            .userName(request.getUserName())
            .email(request.getEmail())
            .birthDate(request.getBirthDate())
            .role(request.getRole())
            .createdAt(LocalDateTime.now())
            .build();

        return appUserRepository.save(appUser);
    }
    

















}