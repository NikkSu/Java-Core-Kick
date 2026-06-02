package by.course.innovice.factory.impl;

import by.course.innovice.entity.CustomDoubleArray;
import by.course.innovice.exception.ArrayProcessingException;
import by.course.innovice.factory.CustomArrayFactory;

public class CustomDoubleArrayFactoryImpl implements CustomArrayFactory<CustomDoubleArray> {

    public CustomDoubleArray createDoubleArray(double[] array) throws ArrayProcessingException {
        boolean isNotNull = array != null;
        if (isNotNull) {
            CustomDoubleArray customDoubleArray = new CustomDoubleArray(array);
            return customDoubleArray;
        } else {
            throw new ArrayProcessingException("Array cannot be null");
        }
    }

    @Override
    public CustomDoubleArray createArray(int[] array) throws ArrayProcessingException {
         throw new ArrayProcessingException("Use createDoubleArray method instead");
    }
}