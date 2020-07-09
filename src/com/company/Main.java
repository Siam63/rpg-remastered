package com.company;
import java.util.*;
import java.io.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Monster Legends");

        while (running) {
            try {
                System.out.println("-------- Main Menu --------");
                System.out.println("1. Play");
                System.out.println("2. Credits");
                System.out.println("3. Exit");
                System.out.print("> ");
                String input = scanner.nextLine();

                if (input.equals("1")) {
                    Game game = new Game();
                    Character character = new Character(1,50, 10, 0, 3000, 0, 80, 50, 0, 0, "Wooden Sword");
                    game.mainMenu(character);
                } else if (input.equals("2")) {
                    System.out.println("-------- Credits --------");
                    System.out.println("Game made by Siam Rahman");
                } else if (input.equals("3")) {
                    System.out.println("Thanks for playing!");
                    running = false;
                } else {
                    throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                System.out.println("Invalid input! Please try again.");
            }
        }
    }
}

