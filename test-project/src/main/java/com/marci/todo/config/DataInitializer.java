package com.marci.todo.config;

import com.marci.todo.model.Priority;
import com.marci.todo.model.Todo;
import com.marci.todo.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TodoRepository todoRepository;

    public DataInitializer(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) {
        if (todoRepository.count() > 0) {
            return;
        }

        todoRepository.save(Todo.builder()
                .title("Learn Spring Boot")
                .description("Understand controllers, services, dependency injection and REST APIs")
                .completed(false)
                .createdAt(LocalDateTime.now().minusDays(2))
                .deadline(LocalDate.now().plusDays(5))
                .priority(Priority.NORMAL)
                .build());

        todoRepository.save(Todo.builder()
                .title("Build a REST API")
                .description("Create CRUD endpoints for Todo items")
                .completed(false)
                .createdAt(LocalDateTime.now().minusDays(1))
                .deadline(LocalDate.now().plusDays(3))
                .priority(Priority.TRIVIAL)
                .build());

        todoRepository.save(Todo.builder()
                .title("Refactor project structure")
                .description("Use controller, service, repository, DTO and exception packages")
                .completed(false)
                .createdAt(LocalDateTime.now())
                .deadline(LocalDate.now().plusDays(7))
                .priority(Priority.CRITICAL)
                .build());
    }
}