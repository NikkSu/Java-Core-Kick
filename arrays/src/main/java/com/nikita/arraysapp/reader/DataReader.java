package com.nikita.arraysapp.reader;

import com.nikita.arraysapp.exception.ArrayProcessingException;
import java.util.List;

public interface DataReader {
    List<String> readLines(String filePath) throws ArrayProcessingException;
}