package com.cg.service;


import com.cg.dao.TodoDAO;
import com.cg.dto.TodoDTO;
import com.cg.entity.Todo;

import java.time.LocalDate;
import java.util.List;

public class TodoService {

    private final TodoDAO todoDAO;

    public TodoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public void createTodo(TodoDTO dto) {
        Todo todo = new Todo(dto.title, dto.description, dto.priority, dto.dueDate);
        todoDAO.addTodo(todo);
    }

    public List<Todo> getAllTodos() {
        return todoDAO.getAllTodos();
    }

    public Todo getTodoById(int id) {
        return todoDAO.getTodoById(id);
    }

    public boolean updateTodo(int id, TodoDTO dto) {
        Todo updated = new Todo(dto.title, dto.description, dto.priority, dto.dueDate);
        updated.setCompleted(todoDAO.getTodoById(id).isCompleted()); // Preserve completion state
        return todoDAO.updateTodo(id, updated);
    }

    public boolean deleteTodo(int id) {
        return todoDAO.deleteTodo(id);
    }

    public boolean toggleCompletion(int id) {
        Todo todo = todoDAO.getTodoById(id);
        if (todo == null) return false;
        todo.setCompleted(!todo.isCompleted());
        return todoDAO.updateTodo(id, todo);
    }

    public List<Todo> searchTodos(String keyword) {
        return todoDAO.searchByKeyword(keyword);
    }

    public List<Todo> filterByPriority(int priority) {
        return todoDAO.filterByPriority(priority);
    }

    public List<Todo> filterByCompletion(boolean completed) {
        return todoDAO.filterByCompletion(completed);
    }

    public List<Todo> filterByDueDateRange(LocalDate from, LocalDate to) {
        return todoDAO.filterByDueDateRange(from, to);
    }

    public List<Todo> sortByPriority() {
        return todoDAO.sortByPriority();
    }

    public List<Todo> sortByDueDate() {
        return todoDAO.sortByDueDate();
    }

    public List<Todo> sortByCreationDate() {
        return todoDAO.sortByCreationDate();
    }
}

