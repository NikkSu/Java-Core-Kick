package by.innovice.arraysapp.service.impl;

import by.innovice.arraysapp.entity.CustomIntArray;
import by.innovice.arraysapp.service.ArrayMathService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ArrayMathServiceImpl implements ArrayMathService {

    private final Logger logger = LogManager.getLogger(ArrayMathServiceImpl.class);

    @Override
    public OptionalInt findMin(CustomIntArray customArray) {
        int length = customArray.length();
        boolean hasElements = length > 0;

        if (hasElements) {
            int[] array = customArray.getArray();
            int min = array[0];

            for (int i = 1; i < length; i++) {
                int currentElement = array[i];
                boolean isLess = currentElement < min;
                if (isLess) {
                    min = currentElement;
                }
            }
            logger.info("Min value found: " + min);
            return OptionalInt.of(min);
        } else {
            logger.warn("Array is empty, cannot find min value");
            return OptionalInt.empty();
        }
    }

    @Override
    public OptionalInt findMax(CustomIntArray customArray) {
        int length = customArray.length();
        boolean hasElements = length > 0;

        if (hasElements) {
            int[] array = customArray.getArray();
            int max = array[0];

            for (int i = 1; i < length; i++) {
                int currentElement = array[i];
                boolean isGreater = currentElement > max;
                if (isGreater) {
                    max = currentElement;
                }
            }
            logger.info("Max value found: " + max);
            return OptionalInt.of(max);
        } else {
            logger.warn("Array is empty, cannot find max value");
            return OptionalInt.empty();
        }
    }

    @Override
    public OptionalInt calculateSum(CustomIntArray customArray) {
        int length = customArray.length();
        boolean hasElements = length > 0;

        if (hasElements) {
            int[] array = customArray.getArray();
            int sum = 0;

            for (int i = 0; i < length; i++) {
                int currentElement = array[i];
                sum = sum + currentElement;
            }
            logger.info("Sum calculated: " + sum);
            return OptionalInt.of(sum);
        } else {
            logger.warn("Array is empty, cannot calculate sum");
            return OptionalInt.empty();
        }
    }

    @Override
    public OptionalDouble calculateAverage(CustomIntArray customArray) {
        boolean isNotNull = customArray != null;

        if (isNotNull) {
            OptionalInt sumOptional = calculateSum(customArray);
            boolean hasSum = sumOptional.isPresent();

            if (hasSum) {
                int sum = sumOptional.getAsInt();
                int length = customArray.length();
                double average = (double) sum / length;

                logger.info("Average calculated: " + average);
                return OptionalDouble.of(average);
            } else {
                logger.warn("Sum is empty, cannot calculate average");
                return OptionalDouble.empty();
            }
        } else {
            logger.warn("CustomArray is null, cannot calculate average");
            return OptionalDouble.empty();
        }
    }
}