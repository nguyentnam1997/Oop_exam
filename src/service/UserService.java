package service;

import entities.User;

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
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public User registerUser(Scanner scanner, ArrayList<User> users) {
        System.out.println("------ REGISTER -------");
        do {
            System.out.println("Please input your username");
            String username = scanner.nextLine();
            for (User us :
                    users) {
                if (username.equalsIgnoreCase(us.getUsername())) {
                    System.out.println("Username already exists, try again!");
                }
                else {
                    do {
                        System.out.println("Please input your email");
                        String email = scanner.nextLine();
                        if (!UserService.isValidEmail(email)) {
                            System.out.println("Invalid email, please try again!");
                            continue;
                        }
                        else {
                            do {
                                System.out.println("Please input your password");
                                String password = scanner.nextLine();
                                if (!UserService.isValidPassword(password)) {
                                    System.out.println("Invalid password, please try again!");
                                    continue;
                                }
                                else {
                                    System.out.println("Register user successful");
                                    return new User(username, email, password);
                                }
                            }
                            while (true);
                        }
                    }
                    while (true);
                    break;
                }
            }
            break;
        }
        while (true);
    }
}
