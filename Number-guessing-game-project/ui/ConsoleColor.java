package ui;

public final class ConsoleColor {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    
    public static final String RED_BG_WHITE_TEXT = "\u001B[41m\u001B[37m";
    public static final String YELLOW_BG_BLACK_TEXT = "\u001B[43m\u001B[30m";
    public static final String GREEN_BG_BLACK_TEXT = "\u001B[42m\u001B[30m";
    public static final String WHITE_BG_BLACK_TEXT = "\u001B[47m\u001B[30m";

    private ConsoleColor() {}
}