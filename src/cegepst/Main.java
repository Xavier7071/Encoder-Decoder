package cegepst;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter the message to encode : ");
        Message message = new Message();
        new Encoder(Terminal.readString(), message);
    }
}
