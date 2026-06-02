package by.course.innovice.service;

import by.course.innovice.entity.CustomIntArray;
import by.course.innovice.service.impl.ArrayMathServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ArrayMathServiceImplTest {

    private static final CustomIntArray NORMAL_ARRAY = new CustomIntArray(new int[]{5, 2, 8, 1, 9});
    private static final CustomIntArray EMPTY_ARRAY = new CustomIntArray(new int[]{});

    private final ArrayMathServiceImpl mathService = new ArrayMathServiceImpl();

    @Test
    void testFindMin_NormalArray_ReturnsMinValue() {
        int expectedMin = 1;

        OptionalInt actualMin = mathService.findMin(NORMAL_ARRAY);

        boolean isPresent = actualMin.isPresent();
        assertTrue(isPresent);
        
        int actualValue = actualMin.getAsInt();
        assertEquals(expectedMin, actualValue); 
    }

    @Test
    void testFindMax_NormalArray_ReturnsMaxValue() {
        int expectedMax = 9;

        OptionalInt actualMax = mathService.findMax(NORMAL_ARRAY);

        boolean isPresent = actualMax.isPresent();
        assertTrue(isPresent);
        
        int actualValue = actualMax.getAsInt();
        assertEquals(expectedMax, actualValue);
    }

    @Test
    void testCalculateSum_NormalArray_ReturnsCorrectSum() {
        int expectedSum = 25;

        OptionalInt actualSum = mathService.calculateSum(NORMAL_ARRAY);

        boolean isPresent = actualSum.isPresent();
        assertTrue(isPresent);
        
        int actualValue = actualSum.getAsInt();
        assertEquals(expectedSum, actualValue);
    }

    @Test
    void testCalculateAverage_NormalArray_ReturnsCorrectAverage() {
        double expectedAverage = 5.0;

        OptionalDouble actualAverage = mathService.calculateAverage(NORMAL_ARRAY);

        boolean isPresent = actualAverage.isPresent();
        assertTrue(isPresent);
        
        double actualValue = actualAverage.getAsDouble();
        assertEquals(expectedAverage, actualValue);
    }

    @Test
    void testFindMin_EmptyArray_ReturnsEmptyOptional() {
        OptionalInt actualMin = mathService.findMin(EMPTY_ARRAY);

        boolean isPresent = actualMin.isPresent();
        assertFalse(isPresent);
    }
}