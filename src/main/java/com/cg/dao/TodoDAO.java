package com.cg.dao;


import com.cg.entity.Todo;

import java.time.LocalDate;
import java.util.List;

public interface TodoDAO {
    void addTodo(Todo todo);
    List<Todo> getAllTodos();
    Todo getTodoById(int id);
    boolean updateTodo(int id, Todo updatedTodo);
    boolean deleteTodo(int id);
    List<Todo> searchByKeyword(String keyword);
    List<Todo> filterByPriority(int priority);
    List<Todo> filterByCompletion(boolean completed);
    List<Todo> filterByDueDateRange(LocalDate from, LocalDate to);
    List<Todo> sortByPriority();
    List<Todo> sortByDueDate();
    List<Todo> sortByCreationDate();
}
