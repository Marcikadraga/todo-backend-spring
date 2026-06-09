package com.marci.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.marci.todo.model.Priority;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTodoRequest {
    private String title;
    private String description;
    private boolean completed;
    private LocalDate deadline;
    private Priority priority;
}
