package org.minctory.api.utils.logger;

import java.io.PrintStream;
import java.util.Scanner;

public class Printer {

    private final PrintStream out = System.out;
    private final Scanner scanner;

    public Printer() {
        this.scanner = new Scanner(System.in);
    }

    public void print(String message) {
        out.println(message);
    }

    public String asking(String message) {
        print(message);
        print("> ");
        return nextLine();
    }

    public int askingInt(String message) {
        print(message);
        print("> ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            MinctoryLogger.LOGGER_MAIN.error("Invalid input.");
            return -1;
        }
    }

    public boolean enter(String message) {
        print(message);
        print("> ");
        return nextLine().isEmpty();
    }

    private String nextLine() {
        if (scanner.nextLine().equals("exit")) {
            System.exit(0);
        }
        return scanner.nextLine();
    }

}
