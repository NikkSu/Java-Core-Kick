package com.nikita.composite.reader;

import com.nikita.composite.exception.TextProcessingException;

public interface TextDataReader {
    String readAllText(String filePath) throws TextProcessingException;
}