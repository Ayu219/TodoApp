package com.cg.dao.implementation;

import com.cg.dao.TodoDAO;
import com.cg.entity.Todo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TodoDAOImpl implements TodoDAO {

    private final Map<Integer, Todo> todoMap = new LinkedHashMap<>();
    private int currentId = 1;

    @Override
    public void addTodo(Todo todo) {
        if(todo.getId()==0){
            todo.setId(currentId++);
        }
        todoMap.put(todo.getId(), todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return new ArrayList<>(todoMap.values());
    }

    @Override
    public Todo getTodoById(int id) {
        return todoMap.get(id);
    }

    @Override
    public boolean updateTodo(int id, Todo updatedTodo) {
        if (!todoMap.containsKey(id)) return false;
        updatedTodo.setId(id);
        todoMap.put(id, updatedTodo);
        return true;
    }

    @Override
    public boolean deleteTodo(int id) {
        return todoMap.remove(id) != null;
    }

    @Override
    public List<Todo> searchByKeyword(String keyword) {
        keyword = keyword.toLowerCase();
        String finalKeyword = keyword;
        return todoMap.values().stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(finalKeyword) ||
                        todo.getDescription().toLowerCase().contains(finalKeyword))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> filterByPriority(int priority) {
        return todoMap.values().stream()
                .filter(todo -> todo.getPriority() == priority)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> filterByCompletion(boolean completed) {
        return todoMap.values().stream()
                .filter(todo -> todo.isCompleted() == completed)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> filterByDueDateRange(LocalDate from, LocalDate to) {
        return todoMap.values().stream()
                .filter(todo -> !todo.getDueDate().isBefore(from) && !todo.getDueDate().isAfter(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> sortByPriority() {
        return todoMap.values().stream()
                .sorted(Comparator.comparingInt(Todo::getPriority))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> sortByDueDate() {
        return todoMap.values().stream()
                .sorted(Comparator.comparing(Todo::getDueDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> sortByCreationDate() {
        // Optional: For now, using the order of insertion (LinkedHashMap preserves insertion order)
        return getAllTodos();
    }
}

