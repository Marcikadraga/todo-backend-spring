package com.marci.todo.dto;

import com.marci.todo.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
