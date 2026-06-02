package by.innovice.arraysapp.factory.impl;

import by.innovice.arraysapp.entity.CustomDoubleArray;
import by.innovice.arraysapp.exception.ArrayProcessingException;
import by.innovice.arraysapp.factory.CustomArrayFactory;

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