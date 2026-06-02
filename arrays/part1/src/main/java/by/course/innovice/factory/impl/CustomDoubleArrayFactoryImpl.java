package by.course.innovice.factory.impl;

import by.course.innovice.entity.CustomDoubleArray;
import by.course.innovice.exception.ArrayProcessingException;
import by.course.innovice.factory.CustomArrayFactory;

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