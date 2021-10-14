package cegepst;

public class Encoder {
    private final Message encoderMessage;

    public Encoder(String userInput, Message message) {
        encoderMessage = message;
        encoderMessage.setAlphabeticMessage(userInput);
        encoderMessage.setMessageLength(userInput.length());
        convertToAscii();
        convertToBinary();
        new Parity(encoderMessage).addParity();
    }

    private void convertToAscii() {
        int[] asciiArray = new int[encoderMessage.getMessageLength()];
        for (int i = 0; i < encoderMessage.getMessageLength(); i++) {
            int ascii = encoderMessage.getAlphabeticMessage().charAt(i);
            asciiArray[i] = ascii;
        }
        encoderMessage.setAsciiMessage(asciiArray);
    }

    private void convertToBinary() {
        String[] binaryArray = new String[encoderMessage.getMessageLength()];
        for (int i = 0; i < encoderMessage.getMessageLength(); i++) {
            int asciiValue = encoderMessage.getAsciiMessage(i);
            StringBuilder binary = new StringBuilder();
            while (asciiValue > 0) {
                if (asciiValue % 2 == 1) {
                    binary.append('1');
                } else {
                    binary.append('0');
                }
                asciiValue /= 2;
            }
            binaryArray[i] = validateBinary(binary.reverse());
        }
        encoderMessage.setBinaryMessage(binaryArray);
    }

    private String validateBinary(StringBuilder binary) {
        do {
            if (binary.length() != 8) {
                binary.insert(0, "0");
            }
        } while (binary.length() != 8);
        return binary.toString();
    }
}
