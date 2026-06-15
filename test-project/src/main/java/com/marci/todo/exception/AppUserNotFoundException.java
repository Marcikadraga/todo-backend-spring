package com.marci.todo.exception;

public class AppUserNotFoundException extends RuntimeException {

    public AppUserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}