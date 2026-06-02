package com.nikita.arraysapp.parser;

import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.parser.impl.DoubleDataParserImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DoubleDataParserImplTest {

    private static final String LINE_TO_PARSE = "1.5, 2.7; 3.0";
    private static final double[] EXPECTED_ARRAY = {1.5, 2.7, 3.0};

    private static final double DELTA = 0.0001;

    private final DoubleDataParserImpl parser = new DoubleDataParserImpl();

    @Test
    void testParseToDoubleArray_ValidLine_ReturnsCorrectArray() throws ArrayProcessingException {
        double[] actualArray = parser.parseToDoubleArray(LINE_TO_PARSE);

        assertArrayEquals(EXPECTED_ARRAY, actualArray, DELTA);
    }
}