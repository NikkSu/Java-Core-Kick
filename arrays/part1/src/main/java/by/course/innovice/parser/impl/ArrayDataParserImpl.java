package by.course.innovice.parser.impl;

import by.course.innovice.exception.ArrayProcessingException;
import by.course.innovice.parser.DataParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayDataParserImpl implements DataParser {

    private final Logger logger = LogManager.getLogger(ArrayDataParserImpl.class);

    // Regex to find delimiters: dashes, spaces, commas, semicolons
    private static final String DELIMITER_REGEX = "[-–;\\s,]+";
    // Regex to find delimiters ONLY at the beginning of the string
    private static final String LEADING_DELIMITER_REGEX = "^[-–;\\s,]+";

    @Override
    public int[] parseToIntArray(String line) throws ArrayProcessingException {
        boolean isNotNull = line != null;

        if (isNotNull) {
            String cleanedLine = line.replaceFirst(LEADING_DELIMITER_REGEX, "");
            boolean isEmpty = cleanedLine.isEmpty();

            if (isEmpty) {
                logger.info("Line contains no numbers, returning empty array");
                return new int[0];
            } else {
                String[] stringNumbers = cleanedLine.split(DELIMITER_REGEX);
                int length = stringNumbers.length;
                int[] numbers = new int[length];

                for (int i = 0; i < length; i++) {
                    String stringNumber = stringNumbers[i];
                    try {
                        int number = Integer.parseInt(stringNumber);
                        numbers[i] = number;
                    } catch (NumberFormatException e) {
                        logger.error("Failed to parse string to int: " + stringNumber, e);
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