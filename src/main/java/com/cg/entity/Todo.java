package com.cg.entity;

import java.time.LocalDate;

public class Todo {
    private int id;
    private String title;
    private String description;
    private int priority; // 1 = High, 2 = Medium, 3 = Low
    private LocalDate dueDate;
    private boolean completed;
    private LocalDate creationDate;


    public Todo(int id, String title, String description, int priority, LocalDate dueDate, boolean completed) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setPriority(priority);
        setDueDate(dueDate);
        setCompleted(completed);
        setCreationDate();
    }

    public Todo(String title, String description, int priority, LocalDate dueDate) {
        this(0, title, description, priority, dueDate, false); // ID will be set by DAO
    }

    public void setCreationDate(){
        this.creationDate = LocalDate.now();
    }

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

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Description: %s | Priority: %d | Due: %s | Completed: %s",
                id, title, description, priority, dueDate.toString(), completed ? "Yes" : "No");
    }
}
