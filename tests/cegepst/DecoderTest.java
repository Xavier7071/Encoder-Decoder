package cegepst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {
    private Message message;
    List<String> binaryArray;

    @BeforeEach
    void setUp() {
        message = new Message();
        binaryArray = new ArrayList<>();
    }

    @Test
    void testDecoderWithoutErrors() {
        binaryArray.add("011000101");
        binaryArray.add("011000011");
        binaryArray.add("011101000");
        binaryArray.add("011011011");
        binaryArray.add("011000011");
        binaryArray.add("011011101");
        binaryArray.add("000101011");
        new Decoder(binaryArray, message);
        assertEquals("batman", message.getAlphabeticMessage());
        assertFalse(message.isErrorDetected());
        assertFalse(message.isCorruptionDetected());
    }

    @Test
    void testDecoderWithLongArray() {
        binaryArray.add("010010000");
        binaryArray.add("011001010");
        binaryArray.add("011011000");
        binaryArray.add("011011000");
        binaryArray.add("011011110");
        binaryArray.add("001000001");
        binaryArray.add("010101111");
        binaryArray.add("011011110");
        binaryArray.add("010110100");
        binaryArray.add("011100100");
        binaryArray.add("011011000");
        binaryArray.add("011001001");
        binaryArray.add("011110101");
        new Decoder(binaryArray, message);
        assertEquals("Hello World", message.getAlphabeticMessage());
    }

    @Test
    void testDecoderWithErrors() {
        binaryArray.add("011000101");
        binaryArray.add("011000011");
        binaryArray.add("011111000");
        binaryArray.add("011011011");
        binaryArray.add("011000011");
        binaryArray.add("011011101");
        binaryArray.add("000101011");
        new Decoder(binaryArray, message);
        assertEquals("batman", message.getAlphabeticMessage());
        assertTrue(message.isErrorDetected());
        assertFalse(message.isCorruptionDetected());
    }

    @Test
    void testDecoderWithCorruption() {
        binaryArray.add("101010001");
        binaryArray.add("011001010");
        binaryArray.add("011011000");
        binaryArray.add("011011000");
        new Decoder(binaryArray, message);
        assertFalse(message.isErrorDetected());
        assertTrue(message.isCorruptionDetected());
    }
}