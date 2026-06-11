package com.nikita.arraysapp.specification.impl;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.specification.Specification;

public class IdSpecification implements Specification<CustomIntArray> {
    private final long desiredId;

    public IdSpecification(long desiredId) {
        this.desiredId = desiredId;
    }

    @Override
    public boolean test(CustomIntArray item) {
        return item.getId() == desiredId;
    }
}