package com.nikita.arraysapp.specification.impl;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.specification.Specification;

public class LengthLessThanSpecification implements Specification<CustomIntArray> {
    private final int maxLength;

    public LengthLessThanSpecification(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean test(CustomIntArray item) {
        return item.length() < maxLength;
    }
}