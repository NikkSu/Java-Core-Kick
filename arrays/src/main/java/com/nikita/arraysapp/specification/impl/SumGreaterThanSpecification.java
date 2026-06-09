package com.nikita.arraysapp.specification.impl;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.service.ArrayMathService;
import com.nikita.arraysapp.specification.Specification;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import com.nikita.arraysapp.warehouse.WarehouseStats;

import java.util.OptionalInt;

public class SumGreaterThanSpecification implements Specification<CustomIntArray> {
    private final int minSum;
    private final ArrayMathService mathService;

    public SumGreaterThanSpecification(int minSum, ArrayMathService mathService) {
        this.minSum = minSum;
        this.mathService = mathService;
    }

    @Override
    public boolean isSatisfied(CustomIntArray item) {
        ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
        WarehouseStats stats = warehouse.getStats(item.getId());

        int sum;
        boolean hasStats = stats != null;

        if (hasStats) {
            sum = stats.sum();
        } else {
            OptionalInt sumOptional = mathService.calculateSum(item);
            sum = sumOptional.orElse(0);
        }

        return sum > minSum;
    }
}