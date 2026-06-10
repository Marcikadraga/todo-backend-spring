package com.marci.todo.controller;

import com.marci.todo.model.Todo;
import com.marci.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;
import com.marci.todo.dto.CreateTodoRequest;
import com.marci.todo.dto.UpdateTodoRequest;

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

    @PostMapping
    public void createTodo(@RequestBody CreateTodoRequest request) {
        todoService.save(request);
    }
    
    @PutMapping("/{id}")
    public void updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        todoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteById(id);
    }

    @GetMapping("/completed")
    public List<Todo> getCompletedTodos() {
        return todoService.getCompletedTodos();
    }
    
    @GetMapping("/pending")
    public List<Todo> getPendingTodos() {
        return todoService.getPendingTodos();
    }
}
