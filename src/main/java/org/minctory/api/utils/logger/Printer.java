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
        return scanner.nextLine();
    }

}
