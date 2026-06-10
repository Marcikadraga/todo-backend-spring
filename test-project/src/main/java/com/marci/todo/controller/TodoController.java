package com.marci.todo.controller;

import com.marci.todo.model.Todo;
import com.marci.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;
import com.marci.todo.dto.CreateTodoRequest;
import com.marci.todo.dto.UpdateTodoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        System.out.println("GET /api/todos/" + id);
        return todoService.getTodoById(id);
    }

    @GetMapping("/completed")
    public List<Todo> getCompletedTodos() {
        return todoService.getCompletedTodos();
    }
    
    @GetMapping("/pending")
    public List<Todo> getPendingTodos() {
        return todoService.getPendingTodos();
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody CreateTodoRequest request) {
        Todo createdTodo = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody UpdateTodoRequest request) {
        Todo updatedTodo = todoService.update(id, request);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteById(id);
    }
}
