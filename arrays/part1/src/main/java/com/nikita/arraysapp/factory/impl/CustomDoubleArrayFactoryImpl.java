package com.nikita.arraysapp.factory.impl;

import com.nikita.arraysapp.entity.CustomDoubleArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.factory.CustomArrayFactory;

public class CustomDoubleArrayFactoryImpl implements CustomArrayFactory<CustomDoubleArray, double[]> {

    @Override
    public CustomDoubleArray createArray(double[] array) throws ArrayProcessingException {
        boolean isNotNull = array != null;
        if (isNotNull) {
            return new CustomDoubleArray(array);
        } else {
            throw new ArrayProcessingException("Array cannot be null");
        }
    }
}