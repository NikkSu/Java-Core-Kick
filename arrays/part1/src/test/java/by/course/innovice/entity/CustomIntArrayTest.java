package by.course.innovice.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CustomIntArrayTest {

    private static final int[] RAW_ARRAY = {1, 2, 3};
    private static final CustomIntArray ARRAY_ONE = new CustomIntArray(new int[]{1, 2, 3});
    private static final CustomIntArray ARRAY_TWO = new CustomIntArray(new int[]{1, 2, 3});
    private static final CustomIntArray DIFFERENT_ARRAY = new CustomIntArray(new int[]{4, 5});

    @Test
    void testEquals_SameValues_ReturnsTrue() {
        assertEquals(ARRAY_ONE, ARRAY_TWO);
    }

    @Test
    void testEquals_DifferentValues_ReturnsFalse() {
        assertNotEquals(ARRAY_ONE, DIFFERENT_ARRAY);
    }

    @Test
    void testHashCode_SameValues_ReturnsSameHash() {
        int hashOne = ARRAY_ONE.hashCode();
        int hashTwo = ARRAY_TWO.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void testConstructor_NullArray_CreatesEmptyArray() {
        int expectedLength = 0;

        CustomIntArray nullCreatedArray = new CustomIntArray(null);

        int actualLength = nullCreatedArray.length();
        assertEquals(expectedLength, actualLength);
    }
}