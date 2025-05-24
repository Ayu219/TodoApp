package com.cg.dto;

import java.time.LocalDate;

public class TodoDTO {
    public String title;
    public String description;
    public int priority; // 1 = High, 2 = Medium, 3 = Low
    public LocalDate dueDate;

    public TodoDTO() {}

    public TodoDTO(String title, String description, int priority, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s | Description: %s | Priority: %d | Due Date: %s",
                title, description, priority, dueDate.toString());
    }
}
