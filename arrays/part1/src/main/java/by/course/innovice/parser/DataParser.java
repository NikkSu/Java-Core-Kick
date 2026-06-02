package by.course.innovice.parser;

import by.course.innovice.exception.ArrayProcessingException;

public interface DataParser {
    int[] parseToIntArray(String line) throws ArrayProcessingException;
}