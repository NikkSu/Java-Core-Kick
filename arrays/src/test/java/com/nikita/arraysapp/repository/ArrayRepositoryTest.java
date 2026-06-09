package com.nikita.arraysapp.repository;

import com.nikita.arraysapp.comparator.CustomArrayComparator;
import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.observer.impl.WarehouseObserverImpl;
import com.nikita.arraysapp.specification.impl.IdSpecification;
import com.nikita.arraysapp.specification.impl.LengthLessThanSpecification;
import com.nikita.arraysapp.specification.impl.SumGreaterThanSpecification;
import com.nikita.arraysapp.service.impl.ArrayMathServiceImpl;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayRepositoryTest {

    private final ArrayRepository repository = ArrayRepository.getInstance();
    private final ArrayMathServiceImpl mathService = new ArrayMathServiceImpl();
    private final WarehouseObserverImpl observer = new WarehouseObserverImpl(mathService);

    private CustomIntArray arrayOne;
    private CustomIntArray arrayTwo;
    private CustomIntArray arrayThree;

    @BeforeEach
    void setUp() throws ArrayProcessingException {
        repository.clear();
        ArrayWarehouse.getInstance().clear();

        repository.setArrayObserver(observer);

        arrayOne = new CustomIntArray(1, new int[]{1, 2, 3});
        arrayTwo = new CustomIntArray(2, new int[]{10, 20, 30});
        arrayThree = new CustomIntArray(3, new int[]{100, -50});

        repository.add(arrayOne);
        repository.add(arrayTwo);
        repository.add(arrayThree);
    }

    @Test
    void testQuery_IdSpecification_ReturnsCorrectArray() {
        int targetId = 2;
        int expectedSize = 1;

        List<CustomIntArray> result = repository.query(new IdSpecification(targetId));

        int actualSize = result.size();
        assertEquals(expectedSize, actualSize);
        
        CustomIntArray found = result.get(0);
        assertEquals(arrayTwo, found);
    }

    @Test
    void testQuery_SumGreaterThanSpecification_ReturnsCorrectArrays() {
        int sumThreshold = 40; // arrayTwo (60) and arrayThree (50) should match
        int expectedSize = 2;

        List<CustomIntArray> result = repository.query(new SumGreaterThanSpecification(sumThreshold));

        int actualSize = result.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void testQuery_LengthLessThanSpecification_ReturnsCorrectArrays() {
        int lengthThreshold = 3; // Only arrayThree (length 2) matches
        int expectedSize = 1;

        List<CustomIntArray> result = repository.query(new LengthLessThanSpecification(lengthThreshold));

        int actualSize = result.size();
        assertEquals(expectedSize, actualSize);
        
        CustomIntArray found = result.get(0);
        assertEquals(arrayThree, found);
    }

    @Test
    void testSort_ByLength_SortsCorrectly() {
        int expectedFirstId = 3;

        repository.sort(CustomArrayComparator.BY_LENGTH);

        List<CustomIntArray> sortedList = repository.getStorage();
        CustomIntArray firstElement = sortedList.get(0);
        long actualFirstId = firstElement.getId();
        
        assertEquals(expectedFirstId, actualFirstId);
    }
}