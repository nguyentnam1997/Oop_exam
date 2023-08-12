package view;

import java.util.Scanner;

public class Menu {
    public void welcomeMenu() {
        System.out.println("====== WELCOME ======");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("Please choose: ");
    }

    public void viewOptionAfterLogin() {
        System.out.println("1. Change your username");
        System.out.println("2. Change your email");
        System.out.println("3. Change your password");
        System.out.println("4. Logout");
        System.out.println("5. Quit");
    }
}
