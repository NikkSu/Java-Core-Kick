package com.nikita.arraysapp.warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ArrayWarehouse {
    private static ArrayWarehouse instance;
    private final Logger logger = LogManager.getLogger(ArrayWarehouse.class);
    private final Map<Long, WarehouseStats> statsMap = new HashMap<>();

    private ArrayWarehouse() {}

    public static ArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new ArrayWarehouse();
        }
        return instance;
    }

    public void put(long id, WarehouseStats stats) {
        statsMap.put(id, stats);
        logger.info("Warehouse statistics updated for Array ID: " + id);
    }

    public WarehouseStats getStats(long id) {
        logger.debug("Fetching statistics for Array ID: " + id);
        return statsMap.get(id);
    }

    public void remove(long id) {
        statsMap.remove(id);
        logger.info("Warehouse statistics removed for Array ID: " + id);
    }
}