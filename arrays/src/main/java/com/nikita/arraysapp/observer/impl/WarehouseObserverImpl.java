package com.nikita.arraysapp.observer.impl;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.observer.ArrayEvent;
import com.nikita.arraysapp.observer.ArrayObserver;
import com.nikita.arraysapp.service.ArrayMathService;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import com.nikita.arraysapp.warehouse.WarehouseStats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WarehouseObserverImpl implements ArrayObserver {

    private final Logger logger = LogManager.getLogger(WarehouseObserverImpl.class);
    private final ArrayMathService mathService;

    public WarehouseObserverImpl(ArrayMathService mathService) {
        this.mathService = mathService;
    }

    @Override
    public void update(ArrayEvent event) {
        boolean isNotNull = event != null;

        if (isNotNull) {
            CustomIntArray array = event.getSource();
            long id = array.getId();

            logger.info("[OBSERVER EVENT] Recalculating stats for Array ID: " + id);

            int sum = mathService.calculateSum(array).orElse(0);
            int min = mathService.findMin(array).orElse(0);
            int max = mathService.findMax(array).orElse(0);
            double avg = mathService.calculateAverage(array).orElse(0.0);

            WarehouseStats stats = new WarehouseStats(sum, min, max, avg);

            ArrayWarehouse.getInstance().put(id, stats);
        }
    }
}