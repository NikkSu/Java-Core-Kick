package com.nikita.arraysapp.specification.impl;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.specification.Specification;
import com.nikita.arraysapp.warehouse.Warehouse;
import com.nikita.arraysapp.warehouse.WarehouseStats;

public class SumGreaterThanSpecification implements Specification<CustomIntArray> {
    private final int minSum;

    public SumGreaterThanSpecification(int minSum) {
        this.minSum = minSum;
    }

    @Override
    public boolean isSatisfied(CustomIntArray item) {
        Warehouse warehouse = Warehouse.getInstance();
        WarehouseStats stats = warehouse.getStats(item.getId());

        if (stats == null) {
            return false;
        }
        
        return stats.sum() > minSum;
    }
}