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

        menu.welcomeMenu();
        int choose = Integer.parseInt(scanner.nextLine());
        if (choose == 1) {
            System.out.println("Please input your username:");
            String username = scanner.nextLine();
            for (User user :
                    users) {
                if (username.equalsIgnoreCase(user.getUsername())) {

                }
            }
        } else if (choose == 2) {
            users.add(userService.registerUser(scanner));
        }
    }
}