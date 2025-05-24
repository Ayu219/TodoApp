package com.cg.service;

import com.cg.dao.implementation.TodoDAOImpl;
import com.cg.dao.TodoDAO;
import com.cg.dto.TodoDTO;
import com.cg.entity.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoServiceTest {

    private TodoService service;

    @BeforeEach
    void setup() {
        TodoDAO dao = new TodoDAOImpl();
        service = new TodoService(dao);
    }

    @Test
    void testCreateAndGetAllTodos() {
        service.createTodo(new TodoDTO("Task 1", "Desc", 1, LocalDate.now()));
        service.createTodo(new TodoDTO("Task 2", "Desc", 2, LocalDate.now().plusDays(1)));

        List<Todo> todos = service.getAllTodos();
        assertEquals(2, todos.size());
    }

    @Test
    void testGetTodoById() {
        service.createTodo(new TodoDTO("Task", "Desc", 1, LocalDate.now()));
        Todo todo = service.getAllTodos().get(0);

        Todo found = service.getTodoById(todo.getId());
        assertEquals(todo.getTitle(), found.getTitle());
    }

    @Test
    void testUpdateTodo() {
        service.createTodo(new TodoDTO("Task", "Desc", 1, LocalDate.now()));
        Todo original = service.getAllTodos().get(0);

        boolean updated = service.updateTodo(original.getId(), new TodoDTO("Updated", "New Desc", 2, LocalDate.now().plusDays(2)));
        assertTrue(updated);

        Todo result = service.getTodoById(original.getId());
        assertEquals("Updated", result.getTitle());
        assertEquals(2, result.getPriority());
    }

    @Test
    void testDeleteTodo() {
        service.createTodo(new TodoDTO("Task", "Desc", 1, LocalDate.now()));
        Todo todo = service.getAllTodos().get(0);

        boolean deleted = service.deleteTodo(todo.getId());
        assertTrue(deleted);
        assertNull(service.getTodoById(todo.getId()));
    }

    @Test
    void testToggleCompletion() {
        service.createTodo(new TodoDTO("Task", "Desc", 1, LocalDate.now()));
        Todo todo = service.getAllTodos().get(0);

        boolean toggled = service.toggleCompletion(todo.getId());
        assertTrue(toggled);

        Todo updated = service.getTodoById(todo.getId());
        assertTrue(updated.isCompleted());
    }

    @Test
    void testSearchTodos() {
        service.createTodo(new TodoDTO("Buy Milk", "Groceries", 1, LocalDate.now()));
        service.createTodo(new TodoDTO("Do Homework", "Math", 2, LocalDate.now()));

        List<Todo> result = service.searchTodos("Milk");
        assertEquals(1, result.size());
    }

    @Test
    void testFilterByPriority() {
        service.createTodo(new TodoDTO("High", "A", 3, LocalDate.now()));
        service.createTodo(new TodoDTO("Low", "B", 1, LocalDate.now()));

        List<Todo> result = service.filterByPriority(3);
        assertEquals(1, result.size());
        assertEquals("High", result.get(0).getTitle());
    }

    @Test
    void testFilterByCompletion() {
        service.createTodo(new TodoDTO("Task", "Desc", 1, LocalDate.now()));
        Todo todo = service.getAllTodos().get(0);
        service.toggleCompletion(todo.getId());

        List<Todo> completed = service.filterByCompletion(true);
        assertEquals(1, completed.size());
    }

    @Test
    void testFilterByDueDateRange() {
        LocalDate now = LocalDate.now();
        service.createTodo(new TodoDTO("Today", "T", 1, now));
        service.createTodo(new TodoDTO("Later", "L", 1, now.plusDays(5)));

        List<Todo> result = service.filterByDueDateRange(now, now.plusDays(2));
        assertEquals(1, result.size());
    }

    @Test
    void testSortByPriority() {
        service.createTodo(new TodoDTO("B", "B", 3, LocalDate.now()));
        service.createTodo(new TodoDTO("A", "A", 1, LocalDate.now()));

        List<Todo> sorted = service.sortByPriority();
        assertEquals("A", sorted.get(0).getTitle());
    }

    @Test
    void testSortByDueDate() {
        service.createTodo(new TodoDTO("Soon", "S", 2, LocalDate.now()));
        service.createTodo(new TodoDTO("Later", "L", 2, LocalDate.now().plusDays(3)));

        List<Todo> sorted = service.sortByDueDate();
        assertEquals("Soon", sorted.get(0).getTitle());
    }

    @Test
    void testSortByCreationDate() {
        service.createTodo(new TodoDTO("First", "1", 1, LocalDate.now()));
        service.createTodo(new TodoDTO("Second", "2", 2, LocalDate.now()));

        List<Todo> sorted = service.sortByCreationDate();
        assertEquals("First", sorted.get(0).getTitle());
    }
}
