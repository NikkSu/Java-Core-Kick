package com.nikita.arraysapp.validator;

import com.nikita.arraysapp.validator.impl.DoubleArrayLineValidatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleArrayLineValidatorImplTest {

    private static final String VALID_DOUBLE_LINE = "1.5, 2.7, 3.0";
    private static final String INVALID_LINE_LETTERS = "1.5, x3";

    private final DoubleArrayLineValidatorImpl validator = new DoubleArrayLineValidatorImpl();

    @Test
    void testIsValid_ValidDoubleLine_ReturnsTrue() {
        boolean actualResult = validator.isValid(VALID_DOUBLE_LINE);

        assertTrue(actualResult);
    }

    @Test
    void testIsValid_InvalidLineWithLetters_ReturnsFalse() {
        boolean actualResult = validator.isValid(INVALID_LINE_LETTERS);

        assertFalse(actualResult);
    }
}