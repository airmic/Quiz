package ru.otus.hw.quiz.config;


import lombok.Data;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

@Data
public class ConsoleContext {
    private Scanner scanner;
    private final PrintStream printStream;

    public ConsoleContext(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    public String next() {
        return this.scanner.next();
    }


}