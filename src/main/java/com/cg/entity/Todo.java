package com.cg.entity;

import java.time.LocalDate;
/**
 * This class represents a Todo item with all its details.
 * It stores things like ID, title, description, priority, due date, 
 * whether it's completed, and when it was created.
 */
public class Todo {
    private int id;
    private String title;
    private String description;
    private int priority; // 1 = High, 2 = Medium, 3 = Low
    private LocalDate dueDate;
    private boolean completed;
    private LocalDate creationDate;


    /**
     * Constructor to create a todo with all fields set.
     * @param id Unique ID of the todo
     * @param title Title of the todo
     * @param description Details of the todo
     * @param priority Priority level (1-High, 2-Medium, 3-Low)
     * @param dueDate When the todo is due
     * @param completed If the todo is completed or not
     */
    public Todo(int id, String title, String description, int priority, LocalDate dueDate, boolean completed) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setPriority(priority);
        setDueDate(dueDate);
        setCompleted(completed);
        setCreationDate();
    }

    /**
     * Constructor to create a new todo without an ID and completed = false.
     * The ID will be assigned later by the DAO.
     */
    public Todo(String title, String description, int priority, LocalDate dueDate) {
        this(0, title, description, priority, dueDate, false); // ID will be set by DAO
    }

    /**
     * Sets the creation date to current date (today).
     */
    public void setCreationDate(){
        this.creationDate = LocalDate.now();
    }

    // Getters and setters for all fields
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns a string showing all todo info in a simple format.
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Description: %s | Priority: %d | Due: %s | Completed: %s",
                id, title, description, priority, dueDate.toString(), completed ? "Yes" : "No");
    }
}
