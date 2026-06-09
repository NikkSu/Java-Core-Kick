package com.nikita.arraysapp.observer;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.observer.impl.WarehouseObserverImpl;
import com.nikita.arraysapp.repository.ArrayRepository;
import com.nikita.arraysapp.service.impl.ArrayMathServiceImpl;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import com.nikita.arraysapp.warehouse.WarehouseStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WarehouseObserverTest {

    private final ArrayRepository repository = ArrayRepository.getInstance();
    private final ArrayWarehouse arrayWarehouse = ArrayWarehouse.getInstance();
    private final ArrayMathServiceImpl mathService = new ArrayMathServiceImpl();

    private final WarehouseObserverImpl observer = new WarehouseObserverImpl(mathService);

    @BeforeEach
    void setUp() {
        repository.clear();
        arrayWarehouse.clear();

        repository.setArrayObserver(observer);
    }

    @Test
    void testObserver_AddArray_CalculatesWarehouseStats() throws ArrayProcessingException {
        CustomIntArray array = new CustomIntArray(10, new int[]{10, 20});
        int expectedSum = 30;

        repository.add(array);

        WarehouseStats stats = arrayWarehouse.getStats(10);
        assertNotNull(stats);

        int actualSum = stats.sum();
        assertEquals(expectedSum, actualSum);
    }

    @Test
    void testObserver_UpdateArrayData_RecalculatesWarehouseStats() throws ArrayProcessingException {
        CustomIntArray array = new CustomIntArray(1, new int[]{10, 20});
        repository.add(array);

        int expectedNewSum = 300;
        array.setArray(new int[]{100, 200});

        WarehouseStats stats = arrayWarehouse.getStats(1);
        int actualNewSum = stats.sum();

        assertEquals(expectedNewSum, actualNewSum);
    }
}