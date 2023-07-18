package org.example.utilitarios;

import java.util.Scanner;

public class InputOutputUtil implements AutoCloseable {

    private Scanner scanner;

    public InputOutputUtil() {
        scanner = new Scanner(System.in);
    }

    public String inputString() {
        return scanner.nextLine();
    }

    public Double inputDouble() {
        double retorno = scanner.nextDouble();
        scanner.nextLine();
        return retorno;
    }

    public Integer inputInt() {
        Integer retorno = scanner.nextInt();
        scanner.nextLine();
        return retorno;
    }

    @Override
    public void close() {
        scanner.close();
    }
}
