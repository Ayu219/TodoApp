package com.cg.ui;


import com.cg.dto.TodoDTO;
import com.cg.entity.Todo;
import com.cg.service.TodoService;
import com.cg.utils.InputValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final TodoService service;

    public UserInterface(TodoService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> createTodo();
                case "2" -> viewAllTodos();
                case "3" -> markTodoComplete();
                case "4" -> editTodo();
                case "5" -> deleteTodo();
                case "6" -> searchTodos();
                case "7" -> filterTodos();
                case "8" -> sortTodos();
                case "0" -> {
                    System.out.println("Exiting Todo App. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- TODO APPLICATION ---");
        System.out.println("1. Create Todo");
        System.out.println("2. View All Todos");
        System.out.println("3. Toggle Completion Status");
        System.out.println("4. Edit Todo");
        System.out.println("5. Delete Todo");
        System.out.println("6. Search Todos");
        System.out.println("7. Filter Todos");
        System.out.println("8. Sort Todos");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void createTodo() {
        System.out.println("\n--- Create Todo ---");
        String title = InputValidator.getNonEmptyString(scanner, "Title: ");
        String desc = InputValidator.getNonEmptyString(scanner, "Description: ");
        int priority = InputValidator.getPriority(scanner);
        LocalDate dueDate = InputValidator.getValidDate(scanner, "Due Date (yyyy-mm-dd): ");

        TodoDTO dto = new TodoDTO(title, desc, priority, dueDate);
        service.createTodo(dto);
        System.out.println("Todo created successfully!");
    }

    private void viewAllTodos() {
        System.out.println("\n--- All Todos ---");
        List<Todo> todos = service.getAllTodos();
        printTodos(todos);
    }

    private void markTodoComplete() {
        int id = InputValidator.getPositiveInt(scanner, "Enter Todo ID to toggle completion: ");
        if (service.toggleCompletion(id)) {
            System.out.println("Todo status updated!");
        } else {
            System.out.println("Todo not found.");
        }
    }

    private void editTodo() {
        int id = InputValidator.getPositiveInt(scanner, "Enter ID to edit: ");
        Todo existing = service.getTodoById(id);
        if (existing == null) {
            System.out.println("Todo not found.");
            return;
        }

        String title = InputValidator.getNonEmptyString(scanner, "New Title: ");
        String desc = InputValidator.getNonEmptyString(scanner, "New Description: ");
        int priority = InputValidator.getPriority(scanner);
        LocalDate dueDate = InputValidator.getValidDate(scanner, "New Due Date (yyyy-mm-dd): ");

        TodoDTO dto = new TodoDTO(title, desc, priority, dueDate);
        if (service.updateTodo(id, dto)) {
            System.out.println("Todo updated.");
        } else {
            System.out.println("Update failed.");
        }
    }

    private void deleteTodo() {
        int id = InputValidator.getPositiveInt(scanner, "Enter ID to delete: ");
        System.out.print("Are you sure? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            if (service.deleteTodo(id)) {
                System.out.println("Todo deleted.");
            } else {
                System.out.println("Todo not found.");
            }
        }
    }

    private void searchTodos() {
        String keyword = InputValidator.getNonEmptyString(scanner, "Enter keyword to search: ");
        List<Todo> results = service.searchTodos(keyword);
        printTodos(results);
    }

    private void filterTodos() {
        System.out.println("\n--- Filter Options ---");
        System.out.println("1. By Priority");
        System.out.println("2. By Completion Status");
        System.out.println("3. By Due Date Range");
        System.out.print("Choose filter type: ");
        String filterType = scanner.nextLine().trim();
        List<Todo> result = null;

        switch (filterType) {
            case "1" -> {
                int p = InputValidator.getPriority(scanner);
                result = service.filterByPriority(p);
            }
            case "2" -> {
                System.out.print("Completed? (true/false): ");
                boolean completed = Boolean.parseBoolean(scanner.nextLine().trim());
                result = service.filterByCompletion(completed);
            }
            case "3" -> {
                LocalDate from = InputValidator.getValidDate(scanner, "From (yyyy-mm-dd): ");
                LocalDate to = InputValidator.getValidDate(scanner, "To (yyyy-mm-dd): ");
                result = service.filterByDueDateRange(from, to);
            }
            default -> System.out.println("Invalid filter type.");
        }

        if (result != null) {
            printTodos(result);
        }
    }

    private void sortTodos() {
        System.out.println("\n--- Sort Options ---");
        System.out.println("1. By Priority");
        System.out.println("2. By Due Date");
        System.out.println("3. By Creation Date");
        System.out.print("Choose sort type: ");
        String sortType = scanner.nextLine().trim();

        List<Todo> result = switch (sortType) {
            case "1" -> service.sortByPriority();
            case "2" -> service.sortByDueDate();
            case "3" -> service.sortByCreationDate();
            default -> {
                System.out.println("Invalid sort option.");
                yield null;
            }
        };

        if (result != null) {
            printTodos(result);
        }
    }

    private void printTodos(List<Todo> todos) {
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
            return;
        }

        for (Todo todo : todos) {
            System.out.printf(
                    "ID: %d | Title: %s | Priority: %d | Due: %s | Completed: %b | Created: %s\n",
                    todo.getId(), todo.getTitle(), todo.getPriority(),
                    todo.getDueDate(), todo.isCompleted(), todo.getCreationDate()
            );
        }
    }
}
