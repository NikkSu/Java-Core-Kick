package by.innovice.arraysapp.parser;

import by.innovice.arraysapp.exception.ArrayProcessingException;

public interface DataParser {
    int[] parseToIntArray(String line) throws ArrayProcessingException;
}