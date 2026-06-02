package com.nikita.arraysapp.parser.impl;

import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.parser.DataParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayDataParserImpl implements DataParser {

    private static final Logger logger = LogManager.getLogger(ArrayDataParserImpl.class);

    private static final String FULL_DELIMITER_REGEX = "[-–;\\s,]+";
    private static final String LEADING_DELIMITER_REGEX = "^[-–;\\s,]+";

    @Override
    public int[] parseToIntArray(String line) throws ArrayProcessingException {
        boolean isNotNull = line != null;

        if (isNotNull) {
            String cleanedLine = line.replaceFirst(LEADING_DELIMITER_REGEX, "");
            boolean isBlank = cleanedLine.isBlank();

            if (isBlank) {
                logger.info("Line contains no numbers, returning empty array");
                return new int[0];
            } else {
                String[] stringNumbers = cleanedLine.split(FULL_DELIMITER_REGEX);
                int length = stringNumbers.length;
                int[] numbers = new int[length];

                for (int i = 0; i < length; i++) {
                    String stringNumber = stringNumbers[i];
                    try {
                        int number = Integer.parseInt(stringNumber);
                        numbers[i] = number;
                    } catch (NumberFormatException e) {
                        throw new ArrayProcessingException("Invalid number format: " + stringNumber, e);
                    }
                }

                logger.info("Successfully parsed string to int array of length: " + length);
                return numbers;
            }
        } else {
            logger.error("Line is null, cannot parse");
            throw new ArrayProcessingException("Line cannot be null");
        }
    }
}