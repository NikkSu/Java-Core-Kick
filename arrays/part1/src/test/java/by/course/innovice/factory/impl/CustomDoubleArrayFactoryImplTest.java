package by.course.innovice.factory.impl;

import by.course.innovice.entity.CustomDoubleArray;
import by.course.innovice.exception.ArrayProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomDoubleArrayFactoryImplTest {

    private static final double[] RAW_ARRAY = {1.5, 2.5};
    private static final CustomDoubleArray EXPECTED_CUSTOM_ARRAY = new CustomDoubleArray(new double[]{1.5, 2.5});

    private final CustomDoubleArrayFactoryImpl factory = new CustomDoubleArrayFactoryImpl();

    @Test
    void testCreateDoubleArray_ValidPrimitiveArray_ReturnsCustomDoubleArray() throws ArrayProcessingException {
        CustomDoubleArray actualCustomArray = factory.createArray(RAW_ARRAY);

        assertEquals(EXPECTED_CUSTOM_ARRAY, actualCustomArray);
    }
}