package ui;

import java.util.Scanner;
import java.util.List;

public class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printHeader() {
        System.out.println(ConsoleColor.CYAN + "=========================================" + ConsoleColor.RESET);
        System.out.println(ConsoleColor.CYAN + "    🎯 NUMBER GUESSING ENGINE v2.6 PRO   " + ConsoleColor.RESET);
        System.out.println(ConsoleColor.CYAN + "=========================================" + ConsoleColor.RESET);
    }

    public static int selectDifficulty() {
        while (true) {
            System.out.println("Select Difficulty:");
            System.out.println("1. Easy   (10 Attempts + Hints)");
            System.out.println("2. Medium (7 Attempts)");
            System.out.println("3. Hard   (5 Attempts - Hardcore)");
            System.out.print("Choose mode (1-3): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1) return 10;
                if (choice == 2) return 7;
                if (choice == 3) return 5;
                System.out.println(ConsoleColor.RED_BG_WHITE_TEXT + " Invalid choice! Enter 1, 2, or 3. " + ConsoleColor.RESET);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColor.RED_BG_WHITE_TEXT + " Numeric input required. " + ConsoleColor.RESET);
            }
            System.out.println();
        }
    }

    public static int getUserGuess(List<Integer> history) {
        while (true) {
            if (!history.isEmpty()) {
                System.out.println(ConsoleColor.PURPLE + "Previous Guesses: " + history + ConsoleColor.RESET);
            }
            System.out.print("Enter your guess (1-100): ");
            String input = scanner.nextLine().trim();
            try {
                int guess = Integer.parseInt(input);
                if (guess >= 1 && guess <= 100) {
                    return guess;
                }
                System.out.println(ConsoleColor.RED_BG_WHITE_TEXT + " Out of range! Input must be between 1 and 100. " + ConsoleColor.RESET);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColor.RED_BG_WHITE_TEXT + " Invalid input format. Enter an integer. " + ConsoleColor.RESET);
            }
            System.out.println();
        }
    }

    public static void printFooter(boolean won, int target, int score) {
        System.out.println(ConsoleColor.CYAN + "------------------------------------------" + ConsoleColor.RESET);
        if (won) {
            System.out.println(ConsoleColor.GREEN_BG_BLACK_TEXT + " 🏆 MISSION ACCOMPLISHED! BULLSEYE FOUND! " + ConsoleColor.RESET);
            System.out.println("Final Score: " + score + " pts");
        } else {
            System.out.println(ConsoleColor.RED_BG_WHITE_TEXT + " ❌ GAME OVER! Max attempts exhausted. " + ConsoleColor.RESET);
            System.out.println("The secret number was: " + ConsoleColor.YELLOW_BG_BLACK_TEXT + " " + target + " " + ConsoleColor.RESET);
        }
        System.out.println(ConsoleColor.CYAN + "------------------------------------------" + ConsoleColor.RESET);
        System.out.println("\n" + ConsoleColor.WHITE_BG_BLACK_TEXT + "   ✨ THANK YOU FOR PLAYING THE GAME! ✨   " + ConsoleColor.RESET + "\n");
    }
}