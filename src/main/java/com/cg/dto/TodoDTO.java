package com.cg.dto;

import java.time.LocalDate;
/**
 * This class is a simple container to hold todo details.
 * It stores title, description, priority, and due date of a todo.
 */
public class TodoDTO {
    public String title;
    public String description;
    public int priority; // 1 = High, 2 = Medium, 3 = Low
    public LocalDate dueDate;

    /**
     * Default constructor to create an empty TodoDTO.
     */
    public TodoDTO() {}

    /**
     * Constructor to create a TodoDTO with all details.
     * @param title The title of the todo
     * @param description The details about the todo
     * @param priority The priority level (1-High, 2-Medium, 3-Low)
     * @param dueDate The due date for the todo
     */
    public TodoDTO(String title, String description, int priority, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    /**
     * Returns a simple string that shows all the todo information in one line.
     */
    @Override
    public String toString() {
        return String.format("Title: %s | Description: %s | Priority: %d | Due Date: %s",
                title, description, priority, dueDate.toString());
    }
}
