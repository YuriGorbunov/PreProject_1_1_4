package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Kolya", "Pupkin", (byte) 23);
        userService.saveUser("Vanya", "Lokov", (byte) 21);
        userService.saveUser("Yura", "Gubkin", (byte) 26);
        userService.saveUser("Petya", "Gazmanov", (byte) 25);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
