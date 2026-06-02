package by.innovice.arraysapp.service.impl;

import by.innovice.arraysapp.entity.CustomIntArray;
import by.innovice.arraysapp.exception.ArrayProcessingException;
import by.innovice.arraysapp.service.ArraySortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArraySortServiceImpl implements ArraySortService {

    private final Logger logger = LogManager.getLogger(ArraySortServiceImpl.class);

    @Override
    public void insertionSort(CustomIntArray customArray) throws ArrayProcessingException {
        boolean isNotNull = customArray != null;

        if (isNotNull) {
            int[] array = customArray.getArray();
            int length = array.length;
            boolean canSort = length > 1;

            if (canSort) {
                for (int i = 1; i < length; i++) {
                    int key = array[i];
                    int j = i - 1;

                    while (j >= 0 && array[j] > key) {
                        array[j + 1] = array[j];
                        j = j - 1;
                    }
                    array[j + 1] = key;
                }
                customArray.setArray(array);
                logger.info("Array sorted using Insertion Sort");
            } else {
                logger.info("Array is too small to sort or empty");
            }
        } else {
            logger.warn("Received null customArray, cannot sort");
        }
    }

    @Override
    public void selectionSort(CustomIntArray customArray) throws ArrayProcessingException {
        boolean isNotNull = customArray != null;

        if (isNotNull) {
            int[] array = customArray.getArray();
            int length = array.length;
            boolean canSort = length > 1;

            if (canSort) {
                int lastIndex = length - 1;
                for (int i = 0; i < lastIndex; i++) {
                    int minIndex = i;
                    int startInner = i + 1;

                    for (int j = startInner; j < length; j++) {
                        int currentMin = array[minIndex];
                        int element = array[j];
                        boolean isLess = element < currentMin;

                        if (isLess) {
                            minIndex = j;
                        }
                    }

                    int temp = array[minIndex];
                    int currentI = array[i];
                    array[minIndex] = currentI;
                    array[i] = temp;
                }
                customArray.setArray(array);
                logger.info("Array sorted using Selection Sort");
            } else {
                logger.info("Array is too small to sort or empty");
            }
        } else {
            logger.warn("Received null customArray, cannot sort");
        }
    }
}