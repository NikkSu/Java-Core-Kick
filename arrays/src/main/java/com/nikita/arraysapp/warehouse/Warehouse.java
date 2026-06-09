package com.nikita.arraysapp.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private final Map<Integer, WarehouseStats> statsMap = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void update(int id, WarehouseStats stats) {
        statsMap.put(id, stats);
    }

    public WarehouseStats getStats(int id) {
        return statsMap.get(id);
    }
}