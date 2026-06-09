package com.nikita.arraysapp.warehouse;

import java.util.HashMap;
import java.util.Map;

public class ArrayWarehouse {
    private static ArrayWarehouse instance;
    private final Map<Long, WarehouseStats> statsMap = new HashMap<Long, WarehouseStats>();

    private ArrayWarehouse() {}

    public static ArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new ArrayWarehouse();
        }
        return instance;
    }

    public void put(long id, WarehouseStats stats) {
        statsMap.put(id, stats);
    }

    public void remove(long id) {
        statsMap.remove(id);
    }

    public void clear() {
        statsMap.clear();
    }

    public WarehouseStats getStats(long id) {
        return statsMap.get(id);
    }
}