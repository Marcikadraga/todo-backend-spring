package com.marci.todo.controller;

import com.marci.todo.dto.CreateAppUserRequest;
import com.marci.todo.dto.UpdateAppUserRequest;
import com.marci.todo.model.AppUser;
import com.marci.todo.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getAllAppUsers() {
        return appUserService.getAllAppUsers();
    }

    @GetMapping("/{id}")
    public AppUser getAppUserById(@PathVariable Long id) {
        return appUserService.getAppUserById(id);
    }

    @GetMapping("/active")
    public List<AppUser> getActiveAppUsers() {
        return appUserService.getActiveAppUsers();
    }

    @GetMapping("/inactive")
    public List<AppUser> getInactiveAppUsers() {
        return appUserService.getInactiveAppUsers();
    }

    @PostMapping
    public ResponseEntity<AppUser> createAppUser(@Valid @RequestBody CreateAppUserRequest request) {
        AppUser createdAppUser = appUserService.createAppUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateAppUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppUserRequest request
    ) {
        AppUser updatedAppUser = appUserService.updateAppUser(id, request);
        return ResponseEntity.ok(updatedAppUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppUserById(@PathVariable Long id) {
        appUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}