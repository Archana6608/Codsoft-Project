package game;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import ui.ConsoleUI;
import ui.ConsoleColor;

public class Main {
    public static void main(String[] args) {
        final int targetNumber = new Random().nextInt(100) + 1;
        final List<Integer> guessHistory = new ArrayList<>();
        
        ConsoleUI.printHeader();
        final int maxAttempts = ConsoleUI.selectDifficulty();
        int attemptsLeft = maxAttempts;
        boolean hasWon = false;

        System.out.println("\nGame started! Total lives: [ " + maxAttempts + " ]\n");

        while (attemptsLeft > 0 && !hasWon) {
            System.out.println("Remaining Attempts: " + ConsoleColor.CYAN + attemptsLeft + ConsoleColor.RESET);
            int currentGuess = ConsoleUI.getUserGuess(guessHistory);
            
            if (guessHistory.contains(currentGuess)) {
                System.out.println(ConsoleColor.YELLOW_BG_BLACK_TEXT + " ⚠️ Already guessed! Choose a new number. " + ConsoleColor.RESET);
                System.out.println();
                continue;
            }

            guessHistory.add(currentGuess);
            attemptsLeft--;

            if (currentGuess == targetNumber) {
                System.out.println(Feedback.CORRECT);
                hasWon = true;
            } else {
                if (currentGuess > targetNumber) {
                    System.out.println(Feedback.TOO_HIGH);
                } else {
                    System.out.println(Feedback.TOO_LOW);
                }

                if (maxAttempts > 5 && attemptsLeft > 1) {
                    String parity = (targetNumber % 2 == 0) ? "EVEN" : "ODD";
                    System.out.println(ConsoleColor.PURPLE + "💡 [SYSTEM HINT]: Target number is " + parity + "." + ConsoleColor.RESET);
                }
            }
            System.out.println();
        }

        int finalScore = hasWon ? (attemptsLeft + 1) * 100 : 0;
        ConsoleUI.printFooter(hasWon, targetNumber, finalScore);
    }
}