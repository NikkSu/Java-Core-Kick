package by.course.innovice.facade;

import by.course.innovice.entity.CustomDoubleArray;
import by.course.innovice.entity.CustomIntArray;
import by.course.innovice.exception.ArrayProcessingException;
import by.course.innovice.factory.CustomArrayFactory;
import by.course.innovice.factory.impl.CustomDoubleArrayFactoryImpl;
import by.course.innovice.parser.DataParser;
import by.course.innovice.parser.DoubleDataParser;
import by.course.innovice.reader.DataReader;
import by.course.innovice.service.ArrayMathService;
import by.course.innovice.service.ArraySortService;
import by.course.innovice.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ArrayProcessingFacade {

    private final Logger logger = LogManager.getLogger(ArrayProcessingFacade.class);

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

    public void processFile(String filePath) {
        try {
            List<String> lines = reader.readLines(filePath);

            for (String line : lines) {
                processSingleLine(line);
            }

        } catch (ArrayProcessingException e) {
            logger.error("Critical error while reading file", e);
        }
    }

    private void processSingleLine(String line) throws ArrayProcessingException {
        boolean isIntValid = intValidator.isValid(line);
        boolean isDoubleValid = doubleValidator.isValid(line);

        if (isIntValid) {
            logger.info("=== [INT ARRAY DETECTED] ===");
            logger.info("Original line: " + line);

            int[] parsedInts = intParser.parseToIntArray(line);
            CustomIntArray customIntArray = intFactory.createArray(parsedInts);
            String arrayString = customIntArray.toString();
            logger.info("Created Entity: " + arrayString);

            mathService.calculateSum(customIntArray);
            sortService.bubbleSort(customIntArray);

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