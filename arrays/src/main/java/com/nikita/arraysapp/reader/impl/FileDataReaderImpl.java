package com.nikita.arraysapp.reader.impl;

import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileDataReaderImpl implements DataReader {

    private static final Logger logger = LogManager.getLogger(FileDataReaderImpl.class);

    @Override
    public List<String> readLines(String filePath) throws ArrayProcessingException {
        boolean isNotNull = filePath != null;

        if (isNotNull) {
            Path path = Path.of(filePath);

            try (Stream<String> lineStream = Files.lines(path)) {
                List<String> lines = lineStream.toList();
                
                logger.info("File successfully read: " + filePath);
                return lines;
                
            } catch (IOException e) {
                logger.error("Error reading file: " + filePath, e);
                throw new ArrayProcessingException("Failed to read file", e);
            }
            
        } else {
            logger.error("File path is null");
            throw new ArrayProcessingException("File path cannot be null");
        }
    }
}