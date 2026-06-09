package com.nikita.arraysapp.service;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.service.impl.ArrayMathServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class ArrayMathServiceImplTest {

    private CustomIntArray normalArray;
    private CustomIntArray emptyArray;
    private final ArrayMathServiceImpl mathService = new ArrayMathServiceImpl();

    @BeforeEach
    void setUp() throws ArrayProcessingException {
        normalArray = new CustomIntArray(1,new int[]{5, 2, 8, 1, 9});
        emptyArray = new CustomIntArray(2,new int[]{});
    }

    @Test
    void testFindMin_NormalArray_ReturnsMinValue() {
        int expectedMin = 1;
        OptionalInt actualMin = mathService.findMin(normalArray);

        assertTrue(actualMin.isPresent());
        assertEquals(expectedMin, actualMin.getAsInt());
    }

    @Test
    void testFindMax_NormalArray_ReturnsMaxValue() {
        int expectedMax = 9;
        OptionalInt actualMax = mathService.findMax(normalArray);

        assertTrue(actualMax.isPresent());
        assertEquals(expectedMax, actualMax.getAsInt());
    }

    @Test
    void testCalculateSum_NormalArray_ReturnsCorrectSum() {
        int expectedSum = 25;
        OptionalInt actualSum = mathService.calculateSum(normalArray);

        assertTrue(actualSum.isPresent());
        assertEquals(expectedSum, actualSum.getAsInt());
    }

    @Test
    void testCalculateAverage_NormalArray_ReturnsCorrectAverage() {
        double expectedAverage = 5.0;
        OptionalDouble actualAverage = mathService.calculateAverage(normalArray);

        assertTrue(actualAverage.isPresent());
        assertEquals(expectedAverage, actualAverage.getAsDouble());
    }

    @Test
    void testFindMin_EmptyArray_ReturnsEmptyOptional() {
        OptionalInt actualMin = mathService.findMin(emptyArray);
        assertFalse(actualMin.isPresent());
    }
}