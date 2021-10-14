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
        System.out.println("Message decoded successfully : " + decodedMessage);
    }

    private void validateMessage() {
        int remainingLength = decoderMessage.getMessageLength();
        for (int i = 0; i < decoderMessage.getMessageLength(); i++) {
            if (decoderMessage.getMessageLength() < 9) {
                detectErrors(0, remainingLength);
                decodeMessage(0, remainingLength - 1);
                break;
            } else if (i % 9 == 0 && i > 0) {
                remainingLength -= 9;
                detectErrors(i - 9, i);
                decodeMessage(i - 9, i - 1);
            }
            if (remainingLength < 9 && remainingLength != 0) {
                detectErrors(decoderMessage.getMessageLength() - remainingLength, decoderMessage.getMessageLength());
                decodeMessage(decoderMessage.getMessageLength() - remainingLength, decoderMessage.getMessageLength() - 1);
                break;
            }
        }
    }

    private void detectErrors(int i, int temp) {
        for (int j = i; j < temp; j++) {
            if (!parity.validateParityBit(decoderMessage.getEncodedBinaryValue(j))) {
                int k = parity.validateColumn(i, temp);
                if (k == 10) {
                    System.out.println("CORRUPTION");
                } else {
                    StringBuilder binary = new StringBuilder(decoderMessage.getEncodedBinaryValue(j));
                    binary.deleteCharAt(k);
                    binary.insert(k, '0');
                    decoderMessage.setEncodedBinaryValue(binary.toString(), j);
                }
            }
        }
    }

    private void decodeMessage(int k, int temp) {
        for (int j = k; j < temp; j++) {
            System.out.println(decoderMessage.getEncodedBinaryValue(j));
            StringBuilder tempString = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                tempString.append(decoderMessage.getEncodedBinaryValue(j).charAt(i));
            }
            int ascii = Integer.parseInt(tempString.toString(), 2);
            char letter = (char) ascii;
            decodedMessage.append(letter);
        }
    }
}
