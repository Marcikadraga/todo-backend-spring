package com.marci.todo.repository;

import com.marci.todo.model.Todo;
import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
    Todo findById(Long id);
    void save(Todo todo);
    void update(Todo todo);
    void deleteById(Long id);
    List<Todo> findCompleted();
    List<Todo> findPending();
}
