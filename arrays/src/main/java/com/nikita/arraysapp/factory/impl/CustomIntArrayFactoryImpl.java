package com.nikita.arraysapp.factory.impl;

import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.factory.CustomArrayFactory;

public class CustomIntArrayFactoryImpl implements CustomArrayFactory<CustomIntArray, int[]> {

    private static int idGenerator = 1;

    @Override
    public CustomIntArray createArray(int[] array) throws ArrayProcessingException {
        boolean isNotNull = array != null;
        if (isNotNull) {
            int currentId = idGenerator++;
            return new CustomIntArray(currentId, array);
        } else {
            throw new ArrayProcessingException("Array cannot be null");
        }
    }
}