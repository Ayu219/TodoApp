package com.cg.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getNonEmptyString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }

    public static int getPriority(Scanner scanner) {
        int priority;
        while (true) {
            System.out.print("Enter priority (1 = High, 2 = Medium, 3 = Low): ");
            try {
                priority = Integer.parseInt(scanner.nextLine().trim());
                if (priority >= 1 && priority <= 3) {
                    return priority;
                } else {
                    System.out.println("Priority must be 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid priority.");
            }
        }
    }

    public static LocalDate getValidDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMAT);
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Due date must be today or a future date.");
                } else {
                    return date;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            }
        }
    }


    public static int getPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid positive integer.");
            }
        }
    }
}

