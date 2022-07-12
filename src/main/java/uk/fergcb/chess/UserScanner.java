package uk.fergcb.chess;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class UserScanner {
    private final Scanner scanner;

    public UserScanner(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public UserScanner() {
        this(System.in);
    }

    public void close() {
        scanner.close();
    }

    /**
     * Read a line from the scanner and normalise it
     *
     * @return A line of cleaned user input
     */
    public String takeLine() {
        return scanner.nextLine()
                .trim()
                .toLowerCase(Locale.ROOT);
    }

    /**
     * Read a string representing a valid chess move from the scanner
     * @return The user-entered move
     */
    public String takeMove() {
        String move;
        do {
            System.out.print(" > ");
            move = scanner.nextLine();
        } while(!isValidMove(move));
        return move;
    }

    private boolean isValidMove(String move) {
        if (move.length() != 4) return false;
        return isValidCell(move.substring(0, 2)) && isValidCell(move.substring(2, 4));
    }

    private boolean isValidCell(String cell) {
        if (cell.length() != 2) return false;
        char col = cell.charAt(0);
        char row = cell.charAt(1);
        return col >= 'a' && col <= 'h' && row >= '1' && row <= '8';
    }

    /**
     * Ask a yes/no question
     *
     * @param prompt   The user-facing question string
     * @param fallback The default response if none is given
     * @return true for input starting with "y", false for "n", otherwise return fallback
     */
    public boolean query(String prompt, boolean fallback) {
        final String msg = prompt + (fallback ? " (Y/n) " : " (y/N) ");
        System.out.print(msg);

        final String res = scanner
                .nextLine()
                .trim()
                .toLowerCase(Locale.ROOT);

        return switch (res) {
            case "y", "yes", "yeah", "yep" -> true;
            case "n", "no", "nah", "nope" -> false;
            default -> fallback;
        };
    }

    /**
     * Ask a yes/no question, defaulting to false if no answer given.
     *
     * @param prompt The user-facing question string
     * @return true for input starting with "y", otherwise false
     */
    public boolean query(String prompt) {
        return query(prompt, false);
    }
}
