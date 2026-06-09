package com.nikita.arraysapp.repository;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.observer.ArrayEvent;
import com.nikita.arraysapp.observer.ArrayObserver;
import com.nikita.arraysapp.specification.Specification;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ArrayRepository {
    private static ArrayRepository instance;
    private final List<CustomIntArray> storage = new ArrayList<>();
    private ArrayObserver arrayObserver;

    private ArrayRepository() {}

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepository();
        }
        return instance;
    }

    public void setArrayObserver(ArrayObserver arrayObserver) {
        this.arrayObserver = arrayObserver;
    }

    public List<CustomIntArray> getStorage() {
        return new ArrayList<>(storage);
    }

    public void add(CustomIntArray array) {
        if (array != null) {
            storage.add(array);

            if (arrayObserver != null) {
                array.attach(arrayObserver);

                ArrayEvent event = new ArrayEvent(array);
                arrayObserver.update(event);
            }
        }
    }

    public void remove(CustomIntArray array) {
        if (array != null) {
            storage.remove(array);

            if (arrayObserver != null) {
                array.detach(arrayObserver);
                ArrayWarehouse.getInstance().remove(array.getId());
            }
        }
    }
    public List<CustomIntArray> streamQuery(Specification<CustomIntArray> spec) {
        Stream<CustomIntArray> stream = storage.stream();

        List<CustomIntArray> result = stream
                .filter(spec::isSatisfied)
                .toList();

        return result;
    }

    public List<CustomIntArray> query(Specification<CustomIntArray> spec) {
        List<CustomIntArray> result = new ArrayList<>();
        for (CustomIntArray array : storage) {
            if (spec.isSatisfied(array)) {
                result.add(array);
            }
        }
        return result;
    }

    public void sort(Comparator<CustomIntArray> comparator) {
        storage.sort(comparator);
    }

    public void clear() {
        storage.clear();
    }
}