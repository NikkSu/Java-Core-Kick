package com.nikita.arraysapp.factory;

import com.nikita.arraysapp.entity.AbstractCustomArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;

public interface CustomArrayFactory<T extends AbstractCustomArray, E> {

    T createArray(E array) throws ArrayProcessingException;

}