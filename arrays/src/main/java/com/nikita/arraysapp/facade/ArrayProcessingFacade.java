package com.nikita.arraysapp.facade;

import com.nikita.arraysapp.entity.CustomDoubleArray;
import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.factory.CustomArrayFactory;
import com.nikita.arraysapp.parser.DataParser;
import com.nikita.arraysapp.parser.DoubleDataParser;
import com.nikita.arraysapp.reader.DataReader;
import com.nikita.arraysapp.repository.ArrayRepository;
import com.nikita.arraysapp.service.ArrayMathService;
import com.nikita.arraysapp.service.ArraySortService;
import com.nikita.arraysapp.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ArrayProcessingFacade {

    private static final Logger logger = LogManager.getLogger(ArrayProcessingFacade.class);

    private final DataReader reader;
    private final StringValidator intValidator;
    private final StringValidator doubleValidator;
    private final DataParser intParser;
    private final DoubleDataParser doubleParser;
    private final CustomArrayFactory<CustomIntArray, int[]> intFactory;
    private final CustomArrayFactory<CustomDoubleArray, double[]> doubleFactory;
    private final ArrayMathService mathService;
    private final ArraySortService sortService;

    public ArrayProcessingFacade(DataReader reader, StringValidator intValidator, StringValidator doubleValidator,
                                 DataParser intParser, DoubleDataParser doubleParser,
                                 CustomArrayFactory<CustomIntArray, int[]> intFactory,
                                 CustomArrayFactory<CustomDoubleArray, double[]> doubleFactory,
                                 ArrayMathService mathService, ArraySortService sortService) {
        this.reader = reader;
        this.intValidator = intValidator;
        this.doubleValidator = doubleValidator;
        this.intParser = intParser;
        this.doubleParser = doubleParser;
        this.intFactory = intFactory;
        this.doubleFactory = doubleFactory;
        this.mathService = mathService;
        this.sortService = sortService;
    }

    public List<CustomIntArray> processFile(String filePath) {
        List<CustomIntArray> resultList = new ArrayList<>();
        try {
            List<String> lines = reader.readLines(filePath);
            for (String line : lines) {
                try {
                    if (intValidator.isValid(line)) {
                        int[] parsedInts = intParser.parseToIntArray(line);
                        CustomIntArray customIntArray = intFactory.createArray(parsedInts);
                        resultList.add(customIntArray);
                    } else {
                        logger.warn("Skipping invalid line: " + line);
                    }
                } catch (ArrayProcessingException e) {
                    logger.error("Failed to process line: " + line, e);
                }
            }
        } catch (ArrayProcessingException e) {
            logger.error("Critical error while reading file", e);
        }
        return resultList;
    }

    private void processSingleLine(String line) throws ArrayProcessingException {
        boolean isIntValid = intValidator.isValid(line);
        boolean isDoubleValid = doubleValidator.isValid(line);

        if (isIntValid) {
            logger.info("=== [INT ARRAY DETECTED] ===");
            int[] parsedInts = intParser.parseToIntArray(line);
            CustomIntArray customIntArray = intFactory.createArray(parsedInts);
            String arrayString = customIntArray.toString();
            logger.info("Created Entity: " + arrayString);

            ArrayRepository.getInstance().add(customIntArray);
            logger.info("Saved Entity to Repository!");

            mathService.calculateSum(customIntArray);
            sortService.insertionSort(customIntArray);

            String sortedArrayString = customIntArray.toString();
            logger.info("Result after sorting: " + sortedArrayString);
            logger.info("============================");

        } else {
            if (isDoubleValid) {
                logger.info("=== [DOUBLE ARRAY DETECTED] ===");
                logger.info("Original line: " + line);

                double[] parsedDoubles = doubleParser.parseToDoubleArray(line);
                CustomDoubleArray customDoubleArray = doubleFactory.createArray(parsedDoubles);
                String arrayString = customDoubleArray.toString();
                logger.info("Created Entity: " + arrayString);
                logger.info("===============================");

            } else {
                logger.warn("=== [GARBAGE DETECTED] ===");
                logger.warn("Skipping invalid line: " + line);
                logger.warn("==========================");
            }
        }
    }
}