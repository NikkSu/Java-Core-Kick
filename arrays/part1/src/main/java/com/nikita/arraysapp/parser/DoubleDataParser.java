package com.nikita.arraysapp.parser;
import com.nikita.arraysapp.exception.ArrayProcessingException;

public interface DoubleDataParser {
    double[] parseToDoubleArray(String line) throws ArrayProcessingException;
}