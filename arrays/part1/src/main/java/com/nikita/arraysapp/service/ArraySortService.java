package com.nikita.arraysapp.service;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;

public interface ArraySortService {

    void insertionSort(CustomIntArray customArray) throws ArrayProcessingException;

    void selectionSort(CustomIntArray customArray) throws ArrayProcessingException;
}