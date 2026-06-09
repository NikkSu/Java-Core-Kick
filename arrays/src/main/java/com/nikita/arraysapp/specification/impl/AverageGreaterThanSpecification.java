package com.nikita.arraysapp.specification.impl;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.specification.Specification;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import com.nikita.arraysapp.warehouse.WarehouseStats;

public class AverageGreaterThanSpecification implements Specification<CustomIntArray> {
    private final double minAverage;

    public AverageGreaterThanSpecification(double minAverage) {
        this.minAverage = minAverage;
    }

    @Override
    public boolean isSatisfied(CustomIntArray item) {
        ArrayWarehouse arrayWarehouse = ArrayWarehouse.getInstance();
        WarehouseStats stats = arrayWarehouse.getStats(item.getId());

        if (stats == null) {
            return false;
        }

        return stats.average() > minAverage;
    }
}