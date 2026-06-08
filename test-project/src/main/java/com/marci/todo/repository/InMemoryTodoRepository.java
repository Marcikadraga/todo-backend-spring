package com.marci.todo.repository;

import com.marci.todo.model.Priority;
import com.marci.todo.model.Todo;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTodoRepository implements TodoRepository {

    private final List<Todo> todos = new ArrayList<>();

    public InMemoryTodoRepository() {
        todos.add(
            Todo.builder()
                .id(1L)
                .title("Learn Spring Boot")
                .description("Understand controllers, services, DI")
                .completed(false)
                .createdAt(LocalDateTime.now().minusDays(2))
                .deadline(LocalDate.now().plusDays(5))
                .priority(Priority.NORMAL)
                .build()
        );

        todos.add(
            Todo.builder()
                .id(2L)
                .title("Build a REST API")
                .description("Create CRUD endpoints for Todo items")
                .completed(false)
                .createdAt(LocalDateTime.now().minusDays(1))
                .deadline(LocalDate.now().plusDays(3))
                .priority(Priority.TRIVIAL)
                .build()
        );

        todos.add(
            Todo.builder()
                .id(3L)
                .title("Refactor project structure")
                .description("Improve service layer and repository pattern")
                .completed(false)
                .createdAt(LocalDateTime.now())
                .deadline(LocalDate.now().plusDays(7))
                .priority(Priority.NORMAL)
                .build()
        );
    }

    @Override
    public List<Todo> findAll() {
        return todos;
    }

    @Override 
    public Todo findById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Todo> findCompleted(){
        return todos.stream()
            .filter(Todo::isCompleted)
            .toList();
    }

    @Override
    public List<Todo> findPending(){
        return todos.stream()
            .filter(todo -> !todo.isCompleted())
            .toList();
    }
    @Override
    public void save(Todo todo){
        todos.add(todo);
    }

    @Override
    public void update(Todo todo) {
        Todo currentTodo = findById(todo.getId());
    
        if (currentTodo == null) {
            return;
        }
    
        currentTodo.setTitle(todo.getTitle());
        currentTodo.setDescription(todo.getDescription());
        currentTodo.setCompleted(todo.isCompleted());
        currentTodo.setDeadline(todo.getDeadline());
        currentTodo.setPriority(todo.getPriority());
    }

    @Override
    public void deleteById(Long id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}
