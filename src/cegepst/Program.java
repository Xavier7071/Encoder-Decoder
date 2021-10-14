package cegepst;

import java.util.*;

public class Program {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            String mode = askMode();
            if (mode.equals("1")) {
                startEncoder();
            } else {
                startDecoder();
            }
        }
    }

    private String askMode() {
        String mode;
        do {
            System.out.println("\n1- Encode a message");
            System.out.println("2- Decode a message");
            System.out.println("Choose the mode you want to use : ");
            mode = scanner.nextLine();
        } while (!mode.equals("1") && !mode.equals("2"));
        return mode;
    }

    private void startEncoder() {
        System.out.println("Enter the message to encode : ");
        Message message = new Message();
        new Encoder(scanner.nextLine(), message);
    }

    private void startDecoder() {
        System.out.println("Enter the binary of the message to decode (9 bits per line) : ");
        System.out.println("Press Enter twice when you are done");
        Message message = new Message();
        new Decoder(readMessageToDecode(), message);
    }

    private List<String> readMessageToDecode() {
        String binary;
        List<String> binaryArray = new ArrayList<>();
        do {
            binary = scanner.nextLine();
            if (validateBinaryMessage(binary)) {
                binaryArray.add(binary);
            }
        } while (!binary.isEmpty());
        return binaryArray;
    }

    private boolean validateBinaryMessage(String binary) {
        char[] chars = binary.toCharArray();
        if (binary.length() != 9) {
            return false;
        }
        for (char c : chars) {
            if (c != '1' && c != '0') {
                return false;
            }
        }
        return true;
    }
}
