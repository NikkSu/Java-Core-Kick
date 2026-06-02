package by.innovice.arraysapp.entity;

import by.innovice.arraysapp.exception.ArrayProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomIntArrayTest {

    private CustomIntArray arrayOne;
    private CustomIntArray arrayTwo;
    private CustomIntArray differentArray;

    @BeforeEach
    void setUp() throws ArrayProcessingException {
        arrayOne = new CustomIntArray(new int[]{1, 2, 3});
        arrayTwo = new CustomIntArray(new int[]{1, 2, 3});
        differentArray = new CustomIntArray(new int[]{4, 5});
    }

    @Test
    void testEquals_SameValues_ReturnsTrue() {
        assertEquals(arrayOne, arrayTwo);
    }

    @Test
    void testEquals_DifferentValues_ReturnsFalse() {
        assertNotEquals(arrayOne, differentArray);
    }

    @Test
    void testHashCode_SameValues_ReturnsSameHash() {
        assertEquals(arrayOne.hashCode(), arrayTwo.hashCode());
    }

    @Test
    void testConstructor_NullArray_ThrowsException() {
        assertThrows(ArrayProcessingException.class, () -> {
            new CustomIntArray(null);
        });
    }
}