package com.nikita.arraysapp.comparator;

import com.nikita.arraysapp.entity.CustomIntArray;
import java.util.Comparator;

public class CustomArrayComparator {

    public static final Comparator<CustomIntArray> BY_ID = new Comparator<CustomIntArray>() {
        @Override
        public int compare(CustomIntArray o1, CustomIntArray o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    public static final Comparator<CustomIntArray> BY_LENGTH = new Comparator<CustomIntArray>() {
        @Override
        public int compare(CustomIntArray o1, CustomIntArray o2) {
            return Integer.compare(o1.length(), o2.length());
        }
    };

    public static final Comparator<CustomIntArray> BY_FIRST_ELEMENT = new Comparator<CustomIntArray>() {
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
    };

    private CustomArrayComparator() {}
}