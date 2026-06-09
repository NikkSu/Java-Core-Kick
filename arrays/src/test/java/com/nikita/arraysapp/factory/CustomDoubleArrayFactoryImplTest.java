package com.nikita.arraysapp.factory;

import com.nikita.arraysapp.entity.CustomDoubleArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.factory.impl.CustomDoubleArrayFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomDoubleArrayFactoryImplTest {

    private static final double[] RAW_ARRAY = {1.5, 2.5};

    private final CustomDoubleArrayFactoryImpl factory = new CustomDoubleArrayFactoryImpl();

    @Test
    void testCreateDoubleArray_ValidPrimitiveArray_ReturnsCustomDoubleArray() throws ArrayProcessingException {
        CustomDoubleArray ExpectedCustomArray = new CustomDoubleArray(new double[]{1.5, 2.5});

        CustomDoubleArray actualCustomArray = factory.createArray(RAW_ARRAY);

        assertEquals(ExpectedCustomArray, actualCustomArray);
    }
}