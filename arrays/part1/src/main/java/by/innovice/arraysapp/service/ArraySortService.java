package by.innovice.arraysapp.service;

import by.innovice.arraysapp.entity.CustomIntArray;
import by.innovice.arraysapp.exception.ArrayProcessingException;

public interface ArraySortService {

    void insertionSort(CustomIntArray customArray) throws ArrayProcessingException;

    void selectionSort(CustomIntArray customArray) throws ArrayProcessingException;
}