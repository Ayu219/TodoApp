package com.cg;

import com.cg.dao.implementation.TodoDAOImpl;
import com.cg.service.TodoService;
import com.cg.ui.UserInterface;

/**
 * Entry point of the Todo application.
 * It sets up the DAO, service, and user interface layers.
 */
public class Main {
	/**
	 * Entry point of the application.
	 * @param args Command-line arguments (not used).
	 */

    public static void main(String[] args) {
        TodoDAOImpl todoDAO = new TodoDAOImpl();
        TodoService todoService = new TodoService(todoDAO);
    }
}
