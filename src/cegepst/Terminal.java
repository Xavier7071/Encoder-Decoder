package cegepst;

import java.util.Scanner;

public class Terminal {
    public static final String RESET = (char) 27 + "[0m";
    public static final String RED = (char) 27 + "[31m";

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
