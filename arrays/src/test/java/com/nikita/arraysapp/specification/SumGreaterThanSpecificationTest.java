package com.nikita.arraysapp.specification;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.service.ArrayMathService;
import com.nikita.arraysapp.specification.impl.SumGreaterThanSpecification;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SumGreaterThanSpecificationTest {

    @Mock
    private ArrayMathService mathService;

    @BeforeEach
    void setUp() {
        ArrayWarehouse.getInstance().clear();
    }

    @Test
    void testIsSatisfied_WarehouseEmpty_CallsMathService() throws ArrayProcessingException {
        CustomIntArray array = new CustomIntArray(1, new int[]{10, 20});

        when(mathService.calculateSum(array)).thenReturn(OptionalInt.of(30));
        
        SumGreaterThanSpecification specification = new SumGreaterThanSpecification(20, mathService);
        boolean result = specification.test(array);

        assertTrue(result);

        verify(mathService, times(1)).calculateSum(array);
    }
}