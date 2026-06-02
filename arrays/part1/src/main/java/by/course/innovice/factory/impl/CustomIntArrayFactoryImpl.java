package by.course.innovice.factory.impl;

import by.course.innovice.entity.CustomIntArray;
import by.course.innovice.exception.ArrayProcessingException;
import by.course.innovice.factory.CustomArrayFactory;

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