package com.nikita.arraysapp.warehouse;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.repository.ArrayRepository;
import com.nikita.arraysapp.service.ArrayMathService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WarehouseCalculator {
    private final Logger logger = LogManager.getLogger(WarehouseCalculator.class);
    private final ArrayMathService mathService;

    public WarehouseCalculator(ArrayMathService mathService) {
        this.mathService = mathService;
    }

    public void recalculateAll() {
        ArrayRepository repository = ArrayRepository.getInstance();
        Warehouse warehouse = Warehouse.getInstance();
        List<CustomIntArray> allArrays = repository.getStorage();

        logger.info("[OBSERVER EVENT] Recalculating Warehouse statistics for " + allArrays.size() + " arrays...");

        for (CustomIntArray array : allArrays) {
            int sum = mathService.calculateSum(array).orElse(0);
            int min = mathService.findMin(array).orElse(0);
            int max = mathService.findMax(array).orElse(0);
            double avg = mathService.calculateAverage(array).orElse(0.0);

            WarehouseStats stats = new WarehouseStats(sum, min, max, avg);
            warehouse.update(array.getId(), stats);

            logger.debug("Updated stats for Array ID " + array.getId() + " -> Sum: " + sum);
        }

        logger.info("[OBSERVER EVENT] Warehouse update complete.");
    }
}