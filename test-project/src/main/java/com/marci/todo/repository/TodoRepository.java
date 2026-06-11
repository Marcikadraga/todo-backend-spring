package com.marci.todo.repository;

import com.marci.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCompletedTrue();
    List<Todo> findByCompletedFalse();
}