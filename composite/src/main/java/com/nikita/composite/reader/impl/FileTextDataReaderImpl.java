package com.nikita.composite.reader.impl;

import com.nikita.composite.exception.TextProcessingException;
import com.nikita.composite.reader.TextDataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileTextDataReaderImpl implements TextDataReader {

    private final Logger logger = LogManager.getLogger(FileTextDataReaderImpl.class);

    @Override
    public String readAllText(String filePath) throws TextProcessingException {
        boolean isNotNull = filePath != null;

        if (isNotNull) {
            Path path = Path.of(filePath);

            try {
                String text = Files.readString(path);
                logger.info("File successfully read: " + filePath);
                return text;
            } catch (IOException e) {
                String errorMessage = e.getMessage();
                logger.error("Failed to read file: " + filePath + " | Reason: " + errorMessage);
                throw new TextProcessingException("Failed to read file", e);
            }

        } else {
            logger.error("File path is null");
            throw new TextProcessingException("File path cannot be null");
        }
    }
}