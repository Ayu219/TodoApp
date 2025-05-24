package com.cg.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * This class helps get correct input from the user.
 * It keeps asking until the user types something valid.
 */
public class InputValidator {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Reads a non-empty string from the user.
     * Keeps asking if the input is empty.
     * @param scanner Scanner to read input
     * @param prompt Message to show the user
     * @return A string that is not empty
     */
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

    /**
     * Reads a priority number (1, 2, or 3) from the user.
     * Keeps asking if input is invalid or out of range.
     * @param scanner Scanner to read input
     * @return priority number (1=High, 2=Medium, 3=Low)
     */
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

    /**
     * Reads a valid date in yyyy-MM-dd format from the user.
     * Keeps asking if the date is wrong or badly formatted.
     * @param scanner Scanner to read input
     * @param prompt Message to show the user
     * @return LocalDate object for the given date
     */
    public static LocalDate getValidDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            }
        }
    }

    /**
     * Reads a positive integer from the user.
     * Keeps asking if input is not a number or less than or equal to zero.
     * @param scanner Scanner to read input
     * @param prompt Message to show the user
     * @return positive integer value
     */
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


