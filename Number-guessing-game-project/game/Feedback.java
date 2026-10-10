package game;

import ui.ConsoleColor;

public enum Feedback {
    CORRECT(ConsoleColor.GREEN_BG_BLACK_TEXT + " 🎯 BULLSEYE! You cracked the code! " + ConsoleColor.RESET),
    TOO_HIGH(ConsoleColor.RED_BG_WHITE_TEXT + " 🎈 Too high! Aim a bit lower. " + ConsoleColor.RESET),
    TOO_LOW(ConsoleColor.YELLOW_BG_BLACK_TEXT + " 📉 Too low! Aim a bit higher. " + ConsoleColor.RESET);

    private final String message;

    Feedback(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}