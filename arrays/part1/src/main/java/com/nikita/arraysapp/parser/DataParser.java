package com.nikita.arraysapp.parser;

import com.nikita.arraysapp.exception.ArrayProcessingException;

public interface DataParser {
    int[] parseToIntArray(String line) throws ArrayProcessingException;
}