package service;

import entities.User;
import view.Show;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    //check điều kiện Password
    public static boolean isValidPassword(String password) {
        // Định dạng regex cho mật khẩu
        String passwordRegex = "^(?=.*[A-Z])(?=.*[.,-_;])(?!.*\\s).{7,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    //check điều kiện email
    public static boolean isValidEmail(String email) {
        // Định dạng regex cho địa chỉ email
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public int checkExistsUsername(int count, String username, ArrayList<User> users) {
        for (User us : users) {
            if (us.getUsername().equalsIgnoreCase(username)) {
                count++;
            }
        }
        return count;
    }
    public int checkExistsEmail(int count, String email, ArrayList<User> users) {
        for (User us : users) {
            if (us.getEmail().equalsIgnoreCase(email)) {
                count++;
            }
        }
        return count;
    }

    public User registerUser(Scanner scanner, ArrayList<User> users) {
        System.out.println("------ REGISTER -------");
        do {
            int count = 0;
            System.out.println("Please input your username:");
            String username = scanner.nextLine();
            count = checkExistsUsername(count, username, users);
            if (count != 0) {
                System.out.println("Username already exists, try again!");
            }
            else {
                    do {
                        count = 0;
                        System.out.println("Please input your email:");
                        String email = scanner.nextLine();
                        if (!UserService.isValidEmail(email)) {
                            System.out.println("Incorrect email, please try again!");
                        }
                        else {
                            count = checkExistsEmail(count, email, users);
                            if (count != 0) {
                                System.out.println("Email already exists, try again!");
                                continue;
                            }
                            do {
                                System.out.println("Please input your password:");
                                String password = scanner.nextLine();
                                if (!UserService.isValidPassword(password)) {
                                    System.out.println("Incorrect password, please try again!");
                                }
                                else {
                                    System.out.println("Register user successful!!!");
                                    return new User(username, email, password);
                                }
                            }
                            while (true);
                        }
                    }
                    while (true);
                }
        }
        while (true);
    }
    public void Login(Scanner scanner, ArrayList<User> users, Show show, UserService userService) {
//        int count = 0;
            if (users.size() == 0) {
                System.out.println("No user exists, please register first!");
                users.add(registerUser(scanner, users));
            }
            else {
                do {
                    int count = 0;
                    System.out.println("Please input your username:");
                    String username = scanner.nextLine();
                    count = checkExistsUsername(count, username, users);
                    if (count == 0) {
                        show.reEnterUserOrBack();
                        int choose = Integer.parseInt(scanner.nextLine());
                        switch (choose) {
                            case 1:
                                continue;
                            case 2:
                                break;
                        }
                    }
                    else {
                        for (User user : users) {
                            if (user.getUsername().equalsIgnoreCase(username)) {
                                checkPassword(scanner, user, show);
                                show.viewOptionAfterLogin();
                                selectOptionAfterLogin(scanner, userService, user, users);
                                break;
                            }
                        }
                    }
//                    System.out.println("Please input your username:");
//                    String username = scanner.nextLine();
//                    for (User user : users) {
//                        if (user.getUsername().equalsIgnoreCase(username)) {
//                            checkPassword(scanner, user);
//                            menu.viewOptionAfterLogin();
//                            selectOptionAfterLogin(scanner, userService, user , users);
//                            break;
//                        }
//                        else {
//                            System.out.println("Incorrect username, please try again!");
//                        }
//                    }
                    break;
                }
                while (true);
            }
    }

    public void checkPassword(Scanner scanner, User user, Show show) {
        do {
            System.out.println("Please input your password:");
            String password = scanner.nextLine();
            if (!user.getPassword().equals(password)) {
                show.reEnterOrForgetPass();
                int choosePw = Integer.parseInt(scanner.nextLine());
                switch (choosePw) {
                    case 1 -> {
                        continue;
                    }
                    case 2 -> {
                        changePassword(scanner, user);
                    }
                }
            }
            else {
                System.out.println("------ Login successful! ------");
            }
            break;
        }
        while (true);
    }

    public void changePassword(Scanner scanner, User user) {
        do {
            System.out.println("Please input your email:");
            String email = scanner.nextLine();
            if (!user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Incorrect email, please try again!");
                continue;
            }
            else {
                do {
                    System.out.println("Please input new password: ");
                    String newPassword = scanner.nextLine();
                    if (!UserService.isValidPassword(newPassword)) {
                        System.out.println("Incorrect password, please try again!");
                        continue;
                    }
                    else {
                        System.out.println("Create new password successful!!!");
                        user.setPassword(newPassword);
                    }
                    break;
                }
                while (true);
            }
            break;
        }
        while (true);
    }
    public void changeUsername(Scanner scanner, User user, ArrayList<User> users) {
        int count = 0;
        do {
            System.out.println("Please re-input your username:");
            String username = scanner.nextLine();
            if (!user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Incorrect username, please try again!");
                continue;
            }
            else {
                do {
                    System.out.println("Please input your password:");
                    String password = scanner.nextLine();
                    if (!user.getPassword().equalsIgnoreCase(password)) {
                        System.out.println("Incorrect password, please try again!");
                        continue;
                    }
                    else {
                        do {
                            System.out.println("Please enter your new username:");
                            String newUsername = scanner.nextLine();
                            for (User us : users) {
                                if (us.getUsername().equalsIgnoreCase(newUsername)) count++;
                            }
                            if (count > 0) {
                                System.out.println("Username already exists, please re-enter");
                                continue;
                            }
                            else {
                                user.setUsername(newUsername);
                                System.out.println("Create new username successful!!!");
                            }
                            break;
                        }
                        while (true);
                    }
                    break;
                }
                while (true);
            }
            break;
        }
        while (true);
    }

    public void selectOptionAfterLogin(Scanner scanner, UserService userService, User user, ArrayList<User> users) {
        do {
            System.out.println("Welcome " + user.getUsername() + " , please choose the options:");
            int select = Integer.parseInt(scanner.nextLine());
            switch (select) {
                case 1:
                    userService.changeUsername(scanner, user, users);
                    continue;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }
            break;
        }
        while (true);
    }
}
