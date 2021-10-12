package cegepst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {
    private Message message;

    @BeforeEach
    void setUp() {
        message = new Message();
    }

    @Test
    void testConversionToAscii() {
        new Encoder("Hello", message);
        assertEquals(72, message.getAsciiMessage(0));
        assertEquals(101, message.getAsciiMessage(1));
        assertEquals(108, message.getAsciiMessage(2));
        assertEquals(108, message.getAsciiMessage(3));
        assertEquals(111, message.getAsciiMessage(4));
    }

    @Test
    void testConversionToBinaryWithParity() {
        new Encoder("World", message);
        assertEquals(10101111, Integer.parseInt(message.getBinaryMessage(0)));
        assertEquals(11011110, Integer.parseInt(message.getBinaryMessage(1)));
        assertEquals(11100100, Integer.parseInt(message.getBinaryMessage(2)));
        assertEquals(11011000, Integer.parseInt(message.getBinaryMessage(3)));
        assertEquals(11001001, Integer.parseInt(message.getBinaryMessage(4)));
    }

    @Test
    void testParityLine() {
        new Encoder("Hello World", message);
        assertEquals("010110100", message.getParityLine());
    }
}
