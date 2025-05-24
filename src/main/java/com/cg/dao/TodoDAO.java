package com.cg.dao;


import com.cg.entity.Todo;

import java.time.LocalDate;
import java.util.List;
/**
 * This interface defines all the basic operations 
 * we can do on a Todo list like add, update, delete,
 * search, filter, and sort.
 */
public interface TodoDAO {
	/**
	 * Add a new todo item.
	 * @param todo The todo item we want to add.
	 */
    void addTodo(Todo todo);
    /**
     * Get all todo items from the list.
     * @return A list of all todos.
     */
    List<Todo> getAllTodos();
    /**
     * Find a todo item by its ID.
     * @param id The ID of the todo we want.
     * @return The todo if found, otherwise null.
     */
    Todo getTodoById(int id);
    /**
     * Update a todo using its ID.
     * @param id The ID of the todo we want to update.
     * @param updatedTodo The new details of the todo.
     * @return true if updated successfully, false if not found.
     */
    boolean updateTodo(int id, Todo updatedTodo);
    /**
     * Delete a todo using its ID.
     * @param id The ID of the todo we want to delete.
     * @return true if deleted, false if not found.
     */
    boolean deleteTodo(int id);
    /**
     * Search for todos using a word in the title or description.
     * @param keyword The word we want to search.
     * @return A list of todos that match the search.
     */
    List<Todo> searchByKeyword(String keyword);
    /**
     * Get todos by their priority level.
     * @param priority The priority we are looking for.
     * @return A list of todos with that priority.
     */
    List<Todo> filterByPriority(int priority);
    /**
     * Get todos based on whether they are completed or not.
     * @param completed true for completed, false for not completed.
     * @return A list of matching todos.
     */
    List<Todo> filterByCompletion(boolean completed);
    /**
     * Get todos that are due between two dates.
     * @param from Starting date.
     * @param to Ending date.
     * @return A list of todos due between the dates.
     */
    List<Todo> filterByDueDateRange(LocalDate from, LocalDate to);
    /**
     * Sort todos by priority in ascending order.
     * @return A sorted list of todos.
     */
    List<Todo> sortByPriority();
    /**
     * Sort todos by due date in ascending order.
     * @return A sorted list of todos.
     */
    List<Todo> sortByDueDate();
    /**
     * Sort todos by the date they were created.
     * @return A sorted list of todos.
     */
    List<Todo> sortByCreationDate();
}
