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
     * Constructor taking parameters title,description,priority and dueDate
     * @param title :Title of the todo
     * @param description: descrption about the todo task
     * @param priority:priority of each task(1=High,2=Medium,3=Low)
     * @param dueDate:date by which the task needs to be completed
     * 
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
