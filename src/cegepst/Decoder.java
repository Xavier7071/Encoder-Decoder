package cegepst;

import java.util.List;

public class Decoder {
    private final Message decoderMessage;

    public Decoder(List<String> binaryArray, Message message) {
        decoderMessage = message;
        decoderMessage.setMessageLength(binaryArray.size());
        decoderMessage.setEncodedBinaryArray(binaryArray);
        for (int i = 0; i < decoderMessage.getMessageLength(); i++) {
            System.out.println(decoderMessage.getEncodedBinaryArray(i));
        }
        //insertMessageInArray();
        //detectErrors();
    }

    private void insertMessageInArray() {
        for (int i = 0; i < decoderMessage.getMessageLength(); i++) {

        }
    }

    private void detectErrors() {

    }
}
