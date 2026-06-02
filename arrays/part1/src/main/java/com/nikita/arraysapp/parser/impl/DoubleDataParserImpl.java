package com.nikita.arraysapp.parser.impl;

import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.parser.DoubleDataParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DoubleDataParserImpl implements DoubleDataParser {

    private static final Logger logger = LogManager.getLogger(DoubleDataParserImpl.class);

    private static final String FULL_DELIMITER_REGEX = "[-–;\\s,]+";
    private static final String LEADING_DELIMITER_REGEX = "^[-–;\\s,]+";

    @Override
    public double[] parseToDoubleArray(String line) throws ArrayProcessingException {
        boolean isNotNull = line != null;

        if (isNotNull) {
            String cleanedLine = line.replaceFirst(LEADING_DELIMITER_REGEX, "");
            boolean isBlank = cleanedLine.isBlank();

            if (isBlank) {
                logger.info("Line is empty, returning empty double array");
                return new double[0];
            } else {
                String[] stringNumbers = cleanedLine.split(FULL_DELIMITER_REGEX);
                int length = stringNumbers.length;
                double[] numbers = new double[length];

                for (int i = 0; i < length; i++) {
                    String stringNumber = stringNumbers[i];
                    try {
                        double number = Double.parseDouble(stringNumber);
                        numbers[i] = number;
                    } catch (NumberFormatException e) {
                        logger.error("Failed to parse string to double: " + stringNumber, e);
                        throw new ArrayProcessingException("Invalid double format: " + stringNumber, e);
                    }
                }
                return numbers;
            }
        } else {
            logger.error("Line is null");
            throw new ArrayProcessingException("Line cannot be null");
        }
    }
}