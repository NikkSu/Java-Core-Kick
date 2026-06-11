package com.nikita.arraysapp.validator;

import com.nikita.arraysapp.validator.impl.ArrayLineValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayLineValidatorImplTest {

    private final ArrayLineValidatorImpl validator = new ArrayLineValidatorImpl();

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3", "1 - 2 - 3", "  3 4 7  ", "1;2;3"})
    void testIsValid_ValidStrings_ReturnsTrue(String validLine) {
        boolean actualResult = validator.isValid(validLine);
        assertTrue(actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1y1 21 32", "1, 2, x3", "1.2.3.4"})
    void testIsValid_InvalidStrings_ReturnsFalse(String invalidLine) {
        boolean actualResult = validator.isValid(invalidLine);
        assertFalse(actualResult);
    }

    @Test
    void testIsValid_NullLine_ReturnsFalse() {
        boolean actualResult = validator.isValid(null);
        assertFalse(actualResult);
    }
}