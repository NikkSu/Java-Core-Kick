package com.nikita.arraysapp.comparator;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import com.nikita.arraysapp.warehouse.WarehouseStats;

import java.util.Comparator;

public enum CustomArrayComparator implements Comparator<CustomIntArray> {

    ID {
        @Override
        public int compare(CustomIntArray o1, CustomIntArray o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    },

    LENGTH {
        @Override
        public int compare(CustomIntArray o1, CustomIntArray o2) {
            return Integer.compare(o1.length(), o2.length());
        }
    },

    FIRST_ELEMENT {
        @Override
        public int compare(CustomIntArray o1, CustomIntArray o2) {
            boolean o1HasElements = o1.length() > 0;
            boolean o2HasElements = o2.length() > 0;

            if (o1HasElements && o2HasElements) {
                int firstO1 = o1.getArray()[0];
                int firstO2 = o2.getArray()[0];
                return Integer.compare(firstO1, firstO2);
            }
            if (!o1HasElements && o2HasElements) {
                return -1;
            }
            if (o1HasElements && !o2HasElements) {
                return 1;
            }
            return 0;
        }
    },

    SUM {
        @Override
        public int compare(CustomIntArray o1, CustomIntArray o2) {
            ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
            WarehouseStats stats1 = warehouse.getStats(o1.getId());
            WarehouseStats stats2 = warehouse.getStats(o2.getId());

            int sum1;
            boolean hasStats1 = stats1 != null;
            if (hasStats1) {
                sum1 = stats1.sum();
            } else {
                sum1 = 0;
            }

            int sum2;
            boolean hasStats2 = stats2 != null;
            if (hasStats2) {
                sum2 = stats2.sum();
            } else {
                sum2 = 0;
            }

            return Integer.compare(sum1, sum2);
        }
    }
}