package by.course.innovice.factory.impl;

import by.course.innovice.entity.CustomIntArray;
import by.course.innovice.exception.ArrayProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomIntArrayFactoryImplTest {

    private static final int[] RAW_ARRAY = {1, 2, 3};

    private static final CustomIntArray EXPECTED_CUSTOM_ARRAY = new CustomIntArray(new int[]{1, 2, 3});

    private final CustomIntArrayFactoryImpl factory = new CustomIntArrayFactoryImpl();

    @Test
    void testCreateArray_ValidPrimitiveArray_ReturnsCustomArray() throws ArrayProcessingException {
        CustomIntArray actualCustomArray = factory.createArray(RAW_ARRAY);

        assertEquals(EXPECTED_CUSTOM_ARRAY, actualCustomArray);
    }
}