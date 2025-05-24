package com.cg.service;


import com.cg.dao.TodoDAO;
import com.cg.dto.TodoDTO;
import com.cg.entity.Todo;

import java.time.LocalDate;
import java.util.List;
/**
 * Service class to handle business logic for Todo operations.
 * This class works with todos to do things like add, get, update, delete, and search them.
 * It uses the DAO to do these tasks.
 */
public class TodoService {

    private final TodoDAO todoDAO;

    /**
     * Constructor to set up the service with a TodoDAO.
     * @param todoDAO The data access object used to manage todos.
     */
    public TodoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    /**
     * Creates a new todo from the given data.
     * @param dto Data transfer object containing todo details.
     */
    public void createTodo(TodoDTO dto) {
        Todo todo = new Todo(dto.title, dto.description, dto.priority, dto.dueDate);
        todoDAO.addTodo(todo);
    }

    /**
     * Gets the full list of todos.
     * @return List of all todos.
     */
    public List<Todo> getAllTodos() {
        return todoDAO.getAllTodos();
    }

    /**
     * Finds a todo by its ID.
     * @param id The todo's unique ID.
     * @return The todo if found, otherwise null.
     */
    public Todo getTodoById(int id) {
        return todoDAO.getTodoById(id);
    }

    /**
     * Updates an existing todo by ID using new data.
     * It keeps the completed status unchanged.
     * @param id The ID of the todo to update.
     * @param dto New data for the todo.
     * @return true if updated successfully, false if todo not found.
     */
    public boolean updateTodo(int id, TodoDTO dto) {
        Todo updated = new Todo(dto.title, dto.description, dto.priority, dto.dueDate);
        updated.setCompleted(todoDAO.getTodoById(id).isCompleted()); // Preserve completion state
        return todoDAO.updateTodo(id, updated);
    }

    /**
     * Deletes a todo by its ID.
     * @param id The ID of the todo to delete.
     * @return true if deleted, false if not found.
     */
    public boolean deleteTodo(int id) {
        return todoDAO.deleteTodo(id);
    }

    /**
     * Changes the completion status of a todo (done/undone).
     * @param id The ID of the todo to toggle.
     * @return true if toggle worked, false if todo not found.
     */
    public boolean toggleCompletion(int id) {
        Todo todo = todoDAO.getTodoById(id);
        if (todo == null) return false;
        todo.setCompleted(!todo.isCompleted());
        return todoDAO.updateTodo(id, todo);
    }

    /**
     * Searches todos by a keyword in title or description.
     * @param keyword The word to look for.
     * @return List of matching todos.
     */
    public List<Todo> searchTodos(String keyword) {
        return todoDAO.searchByKeyword(keyword);
    }

    /**
     * Gets todos filtered by priority level.
     * @param priority The priority to filter by.
     * @return List of todos with the given priority.
     */
    public List<Todo> filterByPriority(int priority) {
        return todoDAO.filterByPriority(priority);
    }

    /**
     * Gets todos filtered by completion status.
     * @param completed true for done todos, false for not done.
     * @return List of todos matching the completion status.
     */
    public List<Todo> filterByCompletion(boolean completed) {
        return todoDAO.filterByCompletion(completed);
    }

    /**
     * Gets todos with due dates within a given range.
     * @param from Start date of the range.
     * @param to End date of the range.
     * @return List of todos due between these dates.
     */
    public List<Todo> filterByDueDateRange(LocalDate from, LocalDate to) {
        return todoDAO.filterByDueDateRange(from, to);
    }

    /**
     * Gets todos sorted by priority (low to high).
     * @return Sorted list of todos.
     */
    public List<Todo> sortByPriority() {
        return todoDAO.sortByPriority();
    }

    /**
     * Gets todos sorted by due date (earliest first).
     * @return Sorted list of todos.
     */
    public List<Todo> sortByDueDate() {
        return todoDAO.sortByDueDate();
    }

    /**
    * Gets todos sorted by creation date (oldest first).
    * @return Sorted list of todos.
    */
    public List<Todo> sortByCreationDate() {
        return todoDAO.sortByCreationDate();
    }
}


