package by.innovice.arraysapp.parser.impl;

import by.innovice.arraysapp.exception.ArrayProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDataParserImplTest {

    private static final String LINE_TO_PARSE = "1, 2; 3 - 4";
    private static final int[] EXPECTED_ARRAY = {1, 2, 3, 4};

    private final ArrayDataParserImpl parser = new ArrayDataParserImpl();

    @Test
    void testParseToIntArray_ValidLine_ReturnsCorrectArray() throws ArrayProcessingException {
        int[] actualArray = parser.parseToIntArray(LINE_TO_PARSE);

        assertArrayEquals(EXPECTED_ARRAY, actualArray);
    }

    @Test
    void testParseToIntArray_NullLine_ThrowsException() {
        String nullLine = null;

        assertThrows(ArrayProcessingException.class, () -> {
            parser.parseToIntArray(nullLine);
        });
    }

    @Test
    void testParseToIntArray_LineWithLetters_ThrowsException() {
        String badLine = "1, 2a, 3";

        assertThrows(ArrayProcessingException.class, () -> {
            parser.parseToIntArray(badLine);
        });
    }

    @Test
    void testParseToIntArray_OnlyDelimiters_ReturnsEmptyArray() throws ArrayProcessingException {
        String emptyLine = "- , ;";
        int expectedLength = 0;

        int[] actualArray = parser.parseToIntArray(emptyLine);

        int actualLength = actualArray.length;
        assertEquals(expectedLength, actualLength);
    }
}