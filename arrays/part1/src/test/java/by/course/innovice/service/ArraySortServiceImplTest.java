package by.course.innovice.service;

import by.course.innovice.entity.CustomIntArray;
import by.course.innovice.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArraySortServiceImplTest {

    private static final CustomIntArray ARRAY_FOR_BUBBLE = new CustomIntArray(new int[]{5, 1, 4, 2, 8});
    private static final CustomIntArray ARRAY_FOR_SELECTION = new CustomIntArray(new int[]{5, 1, 4, 2, 8});
    private static final CustomIntArray EXPECTED_SORTED_ARRAY = new CustomIntArray(new int[]{1, 2, 4, 5, 8});

    private final ArraySortServiceImpl sortService = new ArraySortServiceImpl();

    @Test
    void testBubbleSort_UnsortedArray_SortsCorrectly() {
        sortService.bubbleSort(ARRAY_FOR_BUBBLE);

        assertEquals(EXPECTED_SORTED_ARRAY, ARRAY_FOR_BUBBLE);
    }

    @Test
    void testSelectionSort_UnsortedArray_SortsCorrectly() {
        sortService.selectionSort(ARRAY_FOR_SELECTION);

        assertEquals(EXPECTED_SORTED_ARRAY, ARRAY_FOR_SELECTION);
    }
}