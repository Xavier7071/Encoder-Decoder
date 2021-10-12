package cegepst;

public class Parity {
    private final Message parityMessage;

    public Parity(Message message) {
        parityMessage = message;
        addParity();
        printEncodedMessage();
    }

    public void addParity() {
        String[] binaryArray = new String[parityMessage.getMessageLength()];
        for (int i = 0; i < parityMessage.getMessageLength(); i++) {
            binaryArray[i] = addParityBit(i);
        }
        parityMessage.setBinaryMessage(binaryArray);
    }

    private String addParityBit(int i) {
        String binary = parityMessage.getBinaryMessage(i);
        int nb1 = calculateNbOf1(binary);
        if ((nb1 % 2) != 0)
            binary = binary + "1";
        else
            binary = binary + "0";
        return binary;
    }

    private int calculateNbOf1(String binary) {
        int nb1 = 0;
        for (int j = 0; j < 8; j++) {
            if (binary.charAt(j) == '1')
                nb1++;
        }
        return nb1;
    }

    private void printEncodedMessage() {
        for (int i = 0; i < parityMessage.getMessageLength(); i++) {
            if (i % 8 == 0 && i > 0) {
                printParityLine(i);
            }
            printEncodedCharacter(parityMessage.getBinaryMessage(i), parityMessage.getAlphabeticMessage().charAt(i));
        }
    }

    private void printEncodedCharacter(String binary, char character) {
        System.out.print(character + " = ");
        for (int i = 0; i < 8; i++) {
            System.out.print(binary.charAt(i));
        }
        System.out.println(Terminal.RED + binary.charAt(8) + Terminal.RESET);
    }

    private void printParityLine(int i) {
        StringBuilder parityLine = new StringBuilder();
        for (int k = 0; k < 9; k++) {
            int nb1 = 0;
            for (int j = i - 8; j < i; j++) {
                if (parityMessage.getBinaryMessage(j).charAt(k) == '1') {
                    nb1++;
                }
            }
            if ((nb1 % 2) != 0)
                parityLine.append('1');
            else
                parityLine.append('0');
        }
        parityMessage.setParityLine(parityLine.toString());
        System.out.println("    " + Terminal.RED + parityLine + Terminal.RESET);
    }
}
