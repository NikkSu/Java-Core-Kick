package by.course.innovice.parser.impl;

import by.course.innovice.exception.ArrayProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayDataParserImplTest {

    private static final String LINE_TO_PARSE = "1, 2; 3 - 4";
    private static final int[] EXPECTED_ARRAY = {1, 2, 3, 4};

    private final ArrayDataParserImpl parser = new ArrayDataParserImpl();

    @Test
    void testParseToIntArray_ValidLine_ReturnsCorrectArray() throws ArrayProcessingException {
        int[] actualArray = parser.parseToIntArray(LINE_TO_PARSE);

        assertArrayEquals(EXPECTED_ARRAY, actualArray);
    }
}