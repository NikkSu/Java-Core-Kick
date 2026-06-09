package com.nikita.arraysapp.specification.impl;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.service.ArrayMathService;
import com.nikita.arraysapp.specification.Specification;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import com.nikita.arraysapp.warehouse.WarehouseStats;

import java.util.OptionalDouble;

public class AverageGreaterThanSpecification implements Specification<CustomIntArray> {
    private final double minAverage;
    private final ArrayMathService mathService;

    public AverageGreaterThanSpecification(double minAverage, ArrayMathService mathService) {
        this.minAverage = minAverage;
        this.mathService = mathService;
    }

    @Override
    public boolean isSatisfied(CustomIntArray item) {
        ArrayWarehouse arrayWarehouse = ArrayWarehouse.getInstance();
        WarehouseStats stats = arrayWarehouse.getStats(item.getId());

        double average;
        boolean hasStats = stats != null;

        if (hasStats) {
            average = stats.average();
        } else {
            OptionalDouble avgOptional = mathService.calculateAverage(item);
            average = avgOptional.orElse(0.0);
        }

        return average > minAverage;
    }
}