package com.cg.dao.implementation;

import com.cg.dao.TodoDAO;
import com.cg.entity.Todo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
/**
 * This class handles all todo operations using a LinkedHashMap to store data in memory.
 * It implements the TodoDAO interface and provides methods to add, get, update, delete,
 * search, filter, and sort todo items.
 */
public class TodoDAOImpl implements TodoDAO {
	// Stores all the todos with their ID as the key
    private final Map<Integer, Todo> todoMap = new LinkedHashMap<>();
    private int currentId = 1;

    /**
     * Adds a new todo item and assigns it a unique ID.
     */
    @Override
    public void addTodo(Todo todo) {
        if(todo.getId()==0){
            todo.setId(currentId++);
        }
        todoMap.put(todo.getId(), todo);
    }
    /**
     * Returns a list of all todo items.
     */
    @Override
    public List<Todo> getAllTodos() {
        return new ArrayList<>(todoMap.values());
    }
    
    /**
     * Finds and returns a todo item by its ID.
     * Returns null if the ID is not found.
     */
    @Override
    public Todo getTodoById(int id) {
        return todoMap.get(id);
    }

    /**
     * Updates an existing todo item using its ID.
     * Returns true if updated, or false if not found.
     */
    @Override
    public boolean updateTodo(int id, Todo updatedTodo) {
        if (!todoMap.containsKey(id)) return false;
        updatedTodo.setId(id);
        todoMap.put(id, updatedTodo);
        return true;
    }

    /**
     * Deletes a todo item using its ID.
     * Returns true if removed, or false if not found.
     */
    @Override
    public boolean deleteTodo(int id) {
        return todoMap.remove(id) != null;
    }

    /**
     * Searches for todos that contain the keyword in their title or description.
     */
    @Override
    public List<Todo> searchByKeyword(String keyword) {
        keyword = keyword.toLowerCase();
        String finalKeyword = keyword;
        return todoMap.values().stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(finalKeyword) ||
                        todo.getDescription().toLowerCase().contains(finalKeyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of todos with the given priority level.
     */
    @Override
    public List<Todo> filterByPriority(int priority) {
        return todoMap.values().stream()
                .filter(todo -> todo.getPriority() == priority)
                .collect(Collectors.toList());
    }

    /**
     * Returns todos based on whether they are completed or not.
     */
    @Override
    public List<Todo> filterByCompletion(boolean completed) {
        return todoMap.values().stream()
                .filter(todo -> todo.isCompleted() == completed)
                .collect(Collectors.toList());
    }

    /**
     * Returns todos that are due between two dates.
     */
    @Override
    public List<Todo> filterByDueDateRange(LocalDate from, LocalDate to) {
        return todoMap.values().stream()
                .filter(todo -> !todo.getDueDate().isBefore(from) && !todo.getDueDate().isAfter(to))
                .collect(Collectors.toList());
    }


    /**
     * Returns all todos sorted by priority (lowest to highest).
     */
    @Override
    public List<Todo> sortByPriority() {
        return todoMap.values().stream()
                .sorted(Comparator.comparingInt(Todo::getPriority))
                .collect(Collectors.toList());
    }

    /**
     * Returns all todos sorted by due date (earliest first).
     */
    @Override
    public List<Todo> sortByDueDate() {
        return todoMap.values().stream()
                .sorted(Comparator.comparing(Todo::getDueDate))
                .collect(Collectors.toList());
    }

    /**
     * Returns todos in the order they were added (creation order).
     */
    @Override
    public List<Todo> sortByCreationDate() {
        // Optional: For now, using the order of insertion (LinkedHashMap preserves insertion order)
        return getAllTodos();
    }
}

