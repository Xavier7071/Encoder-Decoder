package cegepst;

public class Parity {
    private final Message parityMessage;

    public Parity(Message message) {
        parityMessage = message;
    }

    public void addParity() {
        String[] binaryArray = new String[parityMessage.getMessageLength()];
        for (int i = 0; i < parityMessage.getMessageLength(); i++) {
            binaryArray[i] = addParityBit(i);
        }
        parityMessage.setBinaryMessage(binaryArray);
        printEncodedMessage();
    }

    public boolean validateParityBit(String binary) {
        int nb1 = 0;
        for (int i = 0; i < 9; i++) {
            if (binary.charAt(i) == '1') {
                nb1++;
            }
        }
        return (nb1 % 2) == 0;
    }

    public int validateColumn(int i, int temp) {
        for (int j = 0; j < 9; j++) {
            int nb1 = 0;
            for (int k = i; k < temp; k++) {
                if (parityMessage.getEncodedBinaryValue(k).charAt(j) == '1') {
                    nb1++;
                }
            }
            if ((nb1 % 2) != 0) {
                return j;
            }
        }
        return 10;
    }

    private String addParityBit(int i) {
        String binary = parityMessage.getBinaryMessage(i);
        int nb1 = calculateNbOf1(binary);
        if ((nb1 % 2) != 0) {
            binary = binary + "1";
        } else {
            binary = binary + "0";
        }
        return binary;
    }

    private void addParityLine(int i, int temp) {
        StringBuilder parityLine = new StringBuilder();
        for (int k = 0; k < 9; k++) {
            int nb1 = 0;
            for (int j = i; j < temp; j++) {
                if (parityMessage.getBinaryMessage(j).charAt(k) == '1') {
                    nb1++;
                }
            }
            if ((nb1 % 2) != 0) {
                parityLine.append('1');
            } else {
                parityLine.append('0');
            }
        }
        parityMessage.setParityLine(parityLine.toString());
        printParityLine();
    }

    private int calculateNbOf1(String binary) {
        int nb1 = 0;
        for (int j = 0; j < 8; j++) {
            if (binary.charAt(j) == '1') {
                nb1++;
            }
        }
        return nb1;
    }

    private void printEncodedMessage() {
        int remainingLength = parityMessage.getMessageLength();
        for (int i = 0; i < parityMessage.getMessageLength(); i++) {
            if (i % 8 == 0 && i > 0) {
                remainingLength -= 8;
                addParityLine(i - 8, i);
            }
            printEncodedCharacter(parityMessage.getBinaryMessage(i), parityMessage.getAlphabeticMessage().charAt(i));
        }
        if (parityMessage.getMessageLength() < 8) {
            addParityLine(0, remainingLength);
        }
        if (remainingLength < 8 && remainingLength != 0 && parityMessage.getMessageLength() >= 8) {
            addParityLine(parityMessage.getMessageLength() - remainingLength, parityMessage.getMessageLength());
        }
    }

    private void printEncodedCharacter(String binary, char character) {
        System.out.print(character + " = ");
        for (int i = 0; i < 8; i++) {
            System.out.print(binary.charAt(i));
        }
        System.out.println(Terminal.RED + binary.charAt(8) + Terminal.RESET);
    }

    private void printParityLine() {
        System.out.println("    " + Terminal.RED + parityMessage.getParityLine() + Terminal.RESET);
    }
}
