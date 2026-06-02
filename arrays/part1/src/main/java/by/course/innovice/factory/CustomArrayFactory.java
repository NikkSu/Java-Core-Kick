package by.course.innovice.factory;

import by.course.innovice.entity.AbstractCustomArray;
import by.course.innovice.exception.ArrayProcessingException;

public interface CustomArrayFactory<T extends AbstractCustomArray, E> {

    T createArray(E array) throws ArrayProcessingException;

}