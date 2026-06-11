package com.nikita.arraysapp.repository;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.observer.ArrayEvent;
import com.nikita.arraysapp.observer.ArrayObserver;
import com.nikita.arraysapp.specification.Specification;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ArrayRepository {
    private static ArrayRepository instance;
    private final Logger logger = LogManager.getLogger(ArrayRepository.class);
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
        boolean isNotNull = array != null;
        if (isNotNull) {
            storage.add(array);
            long id = array.getId();
            logger.info("Array added to repository. Assigned ID: " + id);

            boolean hasObserver = arrayObserver != null;
            if (hasObserver) {
                array.attach(arrayObserver);
                ArrayEvent event = new ArrayEvent(array);
                arrayObserver.update(event);
            }
        }
    }

    public void remove(CustomIntArray array) {
        boolean isNotNull = array != null;
        if (isNotNull) {
            storage.remove(array);
            long id = array.getId();
            logger.info("Array removed from repository. ID: " + id);

            boolean hasObserver = arrayObserver != null;
            if (hasObserver) {
                array.detach(arrayObserver);
                ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
                warehouse.remove(id);
            }
        }
    }

    public List<CustomIntArray> queryClassic(Specification<CustomIntArray> spec) {
        List<CustomIntArray> result = new ArrayList<>();

        for (CustomIntArray array : storage) {
            boolean isSatisfied = spec.test(array);

            if (isSatisfied) {
                result.add(array);
            }
        }
        return result;
    }

    public List<CustomIntArray> functionalQuery(Specification<CustomIntArray> spec) {
        Stream<CustomIntArray> stream = storage.stream();

        List<CustomIntArray> result = stream
                .filter(spec)
                .toList();

        return result;
    }

    public void sort(Comparator<CustomIntArray> comparator) {
        storage.sort(comparator);
        logger.info("Repository storage sorted using custom comparator");
    }

    public void clear() {
        storage.clear();
        logger.info("Repository storage cleared");
    }
}