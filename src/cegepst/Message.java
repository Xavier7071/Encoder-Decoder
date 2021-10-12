package cegepst;

public class Message {
    private String[] binaryMessage;
    private String alphabeticMessage;
    private String parityLine;
    private int[] asciiMessage;
    private int messageLength;

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

    public String getParityLine() {
        return parityLine;
    }

    public void setParityLine(String parityLine) {
        this.parityLine = parityLine;
    }
}
