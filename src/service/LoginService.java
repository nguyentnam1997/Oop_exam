package service;

import entities.User;
import view.Show;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginService {
    public void LoginAndRegisterProgram(Scanner scanner, Show menu, UserService userService, ArrayList<User> users) {
        do {
            menu.welcomeMenu();
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> {
                    userService.Login(scanner, users, menu, userService);
                    continue;
                }
                case 2 -> {
                    users.add(userService.registerUser(scanner, users));
                    continue;
                }
            }
            break;
        }
        while (true);
    }

}
