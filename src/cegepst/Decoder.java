package cegepst;

import java.util.List;

public class Decoder {
    private final Message decoderMessage;
    private final Parity parity;
    private final StringBuilder decodedMessage = new StringBuilder();

    public Decoder(List<String> binaryArray, Message message) {
        decoderMessage = message;
        decoderMessage.setMessageLength(binaryArray.size());
        decoderMessage.setEncodedBinaryArray(binaryArray);
        parity = new Parity(decoderMessage);
        validateMessage();
        decoderMessage.setAlphabeticMessage(decodedMessage.toString());
        printDecodedMessage();
    }

    private void validateMessage() {
        int messageLength = decoderMessage.getMessageLength();
        int remainingLength = messageLength;
        for (int i = 0; i < messageLength; i++) {
            if (!decoderMessage.isCorruptionDetected()) {
                decoderMessage.setErrorDetected(false);
                if (messageLength < 9) {
                    detectLineErrors(0, remainingLength);
                    detectColumnErrors(0, remainingLength);
                    decodeMessage(0, remainingLength - 1);
                    i = messageLength;
                } else if (i % 9 == 0 && i > 0) {
                    remainingLength -= 9;
                    detectLineErrors(i - 9, i);
                    detectColumnErrors(i - 9, i);
                    decodeMessage(i - 9, i - 1);
                }
                if (remainingLength < 9 && remainingLength != 0 && messageLength >= 8) {
                    detectLineErrors(messageLength - remainingLength, messageLength);
                    detectColumnErrors(messageLength - remainingLength, messageLength);
                    decodeMessage(messageLength - remainingLength, messageLength - 1);
                    i = messageLength;
                }
            }
        }
    }

    private void detectLineErrors(int i, int length) {
        for (int j = i; j < length; j++) {
            if (!parity.validateParityBit(decoderMessage.getEncodedBinaryValue(j))) {
                decoderMessage.setErrorDetected(true);
                int k = parity.validateColumn(i, length);
                StringBuilder binary = new StringBuilder(decoderMessage.getEncodedBinaryValue(j));
                binary.deleteCharAt(k);
                binary.insert(k, '0');
                decoderMessage.setEncodedBinaryValue(binary.toString(), j);
            }
        }
    }

    private void detectColumnErrors(int i, int length) {
        int k = parity.validateColumn(i, length);
        if (k != 10 && !decoderMessage.isErrorDetected()) {
            System.out.println("CORRUPTION");
            decoderMessage.setCorruptionDetected(true);
        }
    }

    private void decodeMessage(int k, int length) {
        for (int j = k; j < length; j++) {
            StringBuilder tempString = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                tempString.append(decoderMessage.getEncodedBinaryValue(j).charAt(i));
            }
            int ascii = Integer.parseInt(tempString.toString(), 2);
            char letter = (char) ascii;
            decodedMessage.append(letter);
        }
    }

    private void printDecodedMessage() {
        if (!decoderMessage.isCorruptionDetected()) {
            System.out.println("Message decoded successfully : " + decodedMessage);
        }
    }
}
