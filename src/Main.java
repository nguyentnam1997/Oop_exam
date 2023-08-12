import entities.User;
import service.UserService;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        UserService userService = new UserService();
        Menu menu = new Menu();
        do {
            menu.welcomeMenu();
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 2 -> {
                    users.add(userService.registerUser(scanner, users));
                    continue;
                }
                case 1 -> {
                    userService.Login(scanner, users, menu, userService);

                }
            }
            break;
        }
        while (true);

    }
}