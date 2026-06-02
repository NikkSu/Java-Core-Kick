package by.course.innovice.validator.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayLineValidatorImplTest {

    private static final String VALID_LINE_COMMA = "1, 2, 3";
    private static final String VALID_LINE_DASH = "1 - 2 - 3";
    private static final String INVALID_LINE_LETTERS = "1y1 21 32";
    private static final String INVALID_LINE_DOT = "1.5, 2";

    private final ArrayLineValidatorImpl validator = new ArrayLineValidatorImpl();

    @Test
    void testIsValid_ValidLineWithCommas_ReturnsTrue() {
        boolean actualResult = validator.isValid(VALID_LINE_COMMA);

        assertTrue(actualResult);
    }

    @Test
    void testIsValid_InvalidLineWithLetters_ReturnsFalse() {
        boolean actualResult = validator.isValid(INVALID_LINE_LETTERS);

        assertFalse(actualResult);
    }

    @Test
    void testIsValid_InvalidLineWithDot_ReturnsFalse() {
        boolean actualResult = validator.isValid(INVALID_LINE_DOT);

        assertFalse(actualResult);
    }
}