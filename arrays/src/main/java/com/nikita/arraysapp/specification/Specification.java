package com.nikita.arraysapp.specification;

public interface Specification<T> {
    boolean isSatisfied(T item);
}