package by.innovice.arraysapp.factory.impl;

import by.innovice.arraysapp.entity.CustomIntArray;
import by.innovice.arraysapp.exception.ArrayProcessingException;
import by.innovice.arraysapp.factory.CustomArrayFactory;

public class CustomIntArrayFactoryImpl implements CustomArrayFactory<CustomIntArray, int[]> {

    @Override
    public CustomIntArray createArray(int[] array) throws ArrayProcessingException {
        boolean isNotNull = array != null;
        if (isNotNull) {
            return new CustomIntArray(array);
        } else {
            throw new ArrayProcessingException("Array cannot be null");
        }
    }
}