package by.innovice.arraysapp.factory;

import by.innovice.arraysapp.entity.AbstractCustomArray;
import by.innovice.arraysapp.exception.ArrayProcessingException;

public interface CustomArrayFactory<T extends AbstractCustomArray, E> {

    T createArray(E array) throws ArrayProcessingException;

}