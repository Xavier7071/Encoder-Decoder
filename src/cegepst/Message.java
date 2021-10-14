package cegepst;

import java.util.List;

public class Message {
    private List<String> encodedBinaryArray;
    private String[] binaryMessage;
    private String alphabeticMessage;
    private List<String> parityLineArray;
    private int[] asciiMessage;
    private int messageLength;
    private boolean errorDetected;
    private boolean corruptionDetected;

    public String getAlphabeticMessage() {
        return alphabeticMessage;
    }

    public void setAlphabeticMessage(String alphabeticMessage) {
        this.alphabeticMessage = alphabeticMessage;
    }

    public int getAsciiMessage(int i) {
        return asciiMessage[i];
    }

    public void setAsciiMessage(int[] asciiMessage) {
        this.asciiMessage = asciiMessage;
    }

    public String getBinaryMessage(int i) {
        return binaryMessage[i];
    }

    public void setBinaryMessage(String[] binaryMessage) {
        this.binaryMessage = binaryMessage;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public String getParityLineValue(int i) {
        return parityLineArray.get(i);
    }

    public void setParityLineArray(List<String> parityLineArray) {
        this.parityLineArray = parityLineArray;
    }

    public void setEncodedBinaryArray(List<String> encodedBinaryArray) {
        this.encodedBinaryArray = encodedBinaryArray;
    }

    public String getEncodedBinaryValue(int i) {
        return encodedBinaryArray.get(i);
    }

    public void setEncodedBinaryValue(String encodedBinaryValue, int i) {
        this.encodedBinaryArray.set(i, encodedBinaryValue);
    }

    public boolean isErrorDetected() {
        return errorDetected;
    }

    public void setErrorDetected(boolean errorDetected) {
        this.errorDetected = errorDetected;
    }

    public boolean isCorruptionDetected() {
        return corruptionDetected;
    }

    public void setCorruptionDetected(boolean corruptionDetected) {
        this.corruptionDetected = corruptionDetected;
    }
}
