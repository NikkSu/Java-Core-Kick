package com.nikita.arraysapp.parser;
import com.nikita.arraysapp.exception.ArrayProcessingException;

public interface DoubleDataParser {

    public static final String FULL_DELIMITER_REGEX = "[-–;\\s,]+";
    public static final String LEADING_DELIMITER_REGEX = "^[-–;\\s,]+";

    double[] parseToDoubleArray(String line) throws ArrayProcessingException;
}