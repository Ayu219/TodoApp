package com.cg.dao;


import com.cg.dao.implementation.TodoDAOImpl;
import com.cg.entity.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoDAOTest {

    private TodoDAO todoDAO;

    @BeforeEach
    void setup() {
        todoDAO = new TodoDAOImpl();
    }

    @Test
    void testAddAndGetTodo() {
        Todo todo = new Todo(1, "Test", "Test Desc", 1, LocalDate.now(), false);
        todoDAO.addTodo(todo);

        List<Todo> todos = todoDAO.getAllTodos();
        assertEquals(1, todos.size());
        assertEquals("Test", todos.get(0).getTitle());
    }

    @Test
    void testUpdateTodo() {
        Todo todo = new Todo(1, "Old", "Old Desc", 2, LocalDate.now(), false);
        todoDAO.addTodo(todo);

        Todo updated = new Todo(1, "New", "New Desc", 3, LocalDate.now(), true);
        boolean result = todoDAO.updateTodo(updated.getId(), updated);

        assertTrue(result);
        assertEquals("New", todoDAO.getAllTodos().get(0).getTitle());
        assertTrue(todoDAO.getAllTodos().get(0).isCompleted());
    }

    @Test
    void testDeleteTodo() {
        Todo todo = new Todo(1, "Delete", "To Delete", 1, LocalDate.now(), false);
        todoDAO.addTodo(todo);

        boolean deleted = todoDAO.deleteTodo(1);
        assertTrue(deleted);
        assertTrue(todoDAO.getAllTodos().isEmpty());
    }

    @Test
    void testGetTodoById() {
        Todo todo = new Todo(10, "Find Me", "Description", 1, LocalDate.now(), false);
        todoDAO.addTodo(todo);
        Todo found = todoDAO.getTodoById(10);
        assertNotNull(found);
        assertEquals("Find Me", found.getTitle());
    }

    @Test
    void testGetTodoById_NotFound() {
        Todo found = todoDAO.getTodoById(999);
        assertNull(found);
    }
}
