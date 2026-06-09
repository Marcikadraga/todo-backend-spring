package com.marci.todo.service;

import com.marci.todo.model.Priority;
import com.marci.todo.model.Todo;
import com.marci.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private Long nextId = 4L;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id);
    }


    
    public void save(CreateTodoRequest request) {
        Todo todo = Todo.builder()
                .id(nextId)
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(false)
                .createdAt(LocalDateTime.now())
                .deadline(request.getDeadline())
                .priority(request.getPriority() == null ? Priority.NORMAL : request.getPriority())
                .build();
    
        nextId++;
    
        todoRepository.save(todo);
    }

    public void update(Long id, UpdateTodoRequest request) {
        Todo todo = Todo.builder()
                .id(id)
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(request.isCompleted())
                .deadline(request.getDeadline())
                .priority(request.getPriority())
                .build();
    
        todoRepository.update(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findCompleted();
    }

    public List<Todo> getPendingTodos() {
        return todoRepository.findPending();
    }
}
