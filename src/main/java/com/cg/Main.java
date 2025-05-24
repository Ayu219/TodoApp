package com.cg;

import com.cg.dao.implementation.TodoDAOImpl;
import com.cg.service.TodoService;
import com.cg.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        TodoDAOImpl todoDAO = new TodoDAOImpl();
        TodoService todoService = new TodoService(todoDAO);
        UserInterface ui = new UserInterface(todoService);
        ui.start();
    }
}
