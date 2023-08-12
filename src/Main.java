import entities.User;
import service.UserService;
import view.Show;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        UserService userService = new UserService();
        Show menu = new Show();
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