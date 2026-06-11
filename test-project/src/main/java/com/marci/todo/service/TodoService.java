package com.marci.todo.service;

import com.marci.todo.dto.CreateTodoRequest;
import com.marci.todo.dto.UpdateTodoRequest;
import com.marci.todo.exception.TodoNotFoundException;
import com.marci.todo.model.Priority;
import com.marci.todo.model.Todo;
import com.marci.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Todo createTodo(CreateTodoRequest request) {
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(false)
                .createdAt(LocalDateTime.now())
                .deadline(request.getDeadline())
                .priority(request.getPriority() == null ? Priority.NORMAL : request.getPriority())
                .build();

        return todoRepository.save(todo);
    }

    public Todo update(Long id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());
        todo.setDeadline(request.getDeadline());
        todo.setPriority(request.getPriority() == null ? Priority.NORMAL : request.getPriority());

        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todoRepository.delete(todo);
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompletedTrue();
    }

    public List<Todo> getPendingTodos() {
        return todoRepository.findByCompletedFalse();
    }
}