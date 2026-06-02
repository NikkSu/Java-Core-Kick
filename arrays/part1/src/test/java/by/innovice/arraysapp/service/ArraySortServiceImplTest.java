package by.innovice.arraysapp.service;

import by.innovice.arraysapp.entity.CustomIntArray;
import by.innovice.arraysapp.exception.ArrayProcessingException;
import by.innovice.arraysapp.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArraySortServiceImplTest {

    private CustomIntArray arrayForBubble;
    private CustomIntArray arrayForSelection;
    private CustomIntArray expectedSortedArray;

    private final ArraySortServiceImpl sortService = new ArraySortServiceImpl();

    @BeforeEach
    void setUp() throws ArrayProcessingException {
        arrayForBubble = new CustomIntArray(new int[]{5, 1, 4, 2, 8});
        arrayForSelection = new CustomIntArray(new int[]{5, 1, 4, 2, 8});
        expectedSortedArray = new CustomIntArray(new int[]{1, 2, 4, 5, 8});
    }

    @Test
    void testInsertionSort_UnsortedArray_SortsCorrectly() throws ArrayProcessingException {
        sortService.insertionSort(arrayForBubble);
        assertEquals(expectedSortedArray, arrayForBubble);
    }

    @Test
    void testSelectionSort_UnsortedArray_SortsCorrectly() throws ArrayProcessingException {
        sortService.selectionSort(arrayForSelection);
        assertEquals(expectedSortedArray, arrayForSelection);
    }
}