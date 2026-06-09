package com.nikita.arraysapp.repository;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.observer.Observer;
import com.nikita.arraysapp.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayRepository {
    private static ArrayRepository instance;

    private final List<CustomIntArray> storage = new ArrayList<>();

    private final Observer observer = new Observer();

    private ArrayRepository() {}

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepository();
        }
        return instance;
    }

    public Observer getObserver() {
        return observer;
    }

    public List<CustomIntArray> getStorage() {
        return new ArrayList<>(storage);
    }

    public void add(CustomIntArray array) {
        if (array != null) {
            storage.add(array);
            observer.fireEvent();
        }
    }

    public void remove(CustomIntArray array) {
        if (array != null) {
            storage.remove(array);
            observer.fireEvent();
        }
    }

    public List<CustomIntArray> find(Specification<CustomIntArray> spec) {
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
}