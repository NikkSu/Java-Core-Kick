package com.nikita.arraysapp.parser;

import com.nikita.arraysapp.exception.ArrayProcessingException;

public interface DataParser {

    public static final String FULL_DELIMITER_REGEX = "[-–;\\s,]+";
    public static final String LEADING_DELIMITER_REGEX = "^[-–;\\s,]+";

    int[] parseToIntArray(String line) throws ArrayProcessingException;
}