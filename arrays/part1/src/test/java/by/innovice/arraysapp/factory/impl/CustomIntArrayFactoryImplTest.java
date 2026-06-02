package by.innovice.arraysapp.factory.impl;

import by.innovice.arraysapp.entity.CustomIntArray;
import by.innovice.arraysapp.exception.ArrayProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomIntArrayFactoryImplTest {

    private static final int[] RAW_ARRAY = {1, 2, 3};

    private final CustomIntArrayFactoryImpl factory = new CustomIntArrayFactoryImpl();

    @Test
    void testCreateArray_ValidPrimitiveArray_ReturnsCustomArray() throws ArrayProcessingException {
        CustomIntArray expectedCustomArray = new CustomIntArray(new int[]{1, 2, 3});

        CustomIntArray actualCustomArray = factory.createArray(RAW_ARRAY);

        assertEquals(expectedCustomArray, actualCustomArray);
    }

    @Test
    void testCreateArray_NullArray_ThrowsException() {
        int[] nullArray = null;

        assertThrows(ArrayProcessingException.class, () -> {
            factory.createArray(nullArray);
        });
    }
}