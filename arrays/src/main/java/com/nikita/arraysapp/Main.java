package com.nikita.arraysapp;

import com.nikita.arraysapp.comparator.CustomArrayComparator;
import com.nikita.arraysapp.entity.CustomIntArray;
import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.facade.ArrayProcessingFacade;
import com.nikita.arraysapp.factory.impl.CustomDoubleArrayFactoryImpl;
import com.nikita.arraysapp.factory.impl.CustomIntArrayFactoryImpl;
import com.nikita.arraysapp.parser.impl.ArrayDataParserImpl;
import com.nikita.arraysapp.parser.impl.DoubleDataParserImpl;
import com.nikita.arraysapp.reader.impl.FileDataReaderImpl;
import com.nikita.arraysapp.repository.ArrayRepository;
import com.nikita.arraysapp.service.impl.ArrayMathServiceImpl;
import com.nikita.arraysapp.service.impl.ArraySortServiceImpl;
import com.nikita.arraysapp.specification.impl.SumGreaterThanSpecification;
import com.nikita.arraysapp.validator.impl.ArrayLineValidatorImpl;
import com.nikita.arraysapp.validator.impl.DoubleArrayLineValidatorImpl;
import com.nikita.arraysapp.warehouse.ArrayWarehouse;
import com.nikita.arraysapp.warehouse.WarehouseStats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("=== STARTING SYSTEM ===");

        FileDataReaderImpl reader = new FileDataReaderImpl();
        ArrayLineValidatorImpl intValidator = new ArrayLineValidatorImpl();
        DoubleArrayLineValidatorImpl doubleValidator = new DoubleArrayLineValidatorImpl();
        ArrayDataParserImpl intParser = new ArrayDataParserImpl();
        DoubleDataParserImpl doubleParser = new DoubleDataParserImpl();
        CustomIntArrayFactoryImpl intFactory = new CustomIntArrayFactoryImpl();
        CustomDoubleArrayFactoryImpl doubleFactory = new CustomDoubleArrayFactoryImpl();
        ArrayMathServiceImpl mathService = new ArrayMathServiceImpl();
        ArraySortServiceImpl sortService = new ArraySortServiceImpl();

        ArrayProcessingFacade facade = new ArrayProcessingFacade(
                reader, intValidator, doubleValidator,
                intParser, doubleParser,
                intFactory, doubleFactory,
                mathService, sortService
        );

        ArrayRepository repository = ArrayRepository.getInstance();
        ArrayWarehouse arrayWarehouse = ArrayWarehouse.getInstance();

        com.nikita.arraysapp.observer.impl.WarehouseObserverImpl observer =
                new com.nikita.arraysapp.observer.impl.WarehouseObserverImpl(mathService);

        repository.setArrayObserver(observer);

        logger.info("\n=== PROCESSING FILE ===");
        facade.processFile("arrays/data/arrays.txt");


        logger.info("\n=== WAREHOUSE STATS (Auto-calculated by Observer) ===");
        List<CustomIntArray> storage = repository.getStorage();
        for (CustomIntArray arr : storage) {
            WarehouseStats stats = arrayWarehouse.getStats(arr.getId());
            if (stats != null) {
                logger.info(String.format("Array ID: %d | Sum=%d, Max=%d, Min=%d, Avg=%.2f",
                        arr.getId(), stats.sum(), stats.max(), stats.min(), stats.average()));
            } else {
                logger.warn("Stats for Array ID " + arr.getId() + " not found!");
            }
        }

        logger.info("\n=== TEST: ADDING NEW ARRAY ===");
        try {
            CustomIntArray newArray = intFactory.createArray(new int[]{900, 100, 55, 44});
            logger.info("Created manual array with ID: " + newArray.getId());

            repository.add(newArray);

            WarehouseStats newStats = arrayWarehouse.getStats(newArray.getId());
            if (newStats != null) {
                logger.info("Auto-calculated stats for NEW array: Sum=" + newStats.sum());
            }
        } catch (ArrayProcessingException e) {
            logger.error("Error creating manual array", e);
        }


        logger.info("\n=== SPECIFICATION SEARCH ===");
        int sumThreshold = 50;
        logger.info("Looking for arrays with SUM > " + sumThreshold);

        List<CustomIntArray> richArrays = repository.query(new SumGreaterThanSpecification(sumThreshold, mathService));
        for (CustomIntArray arr : richArrays) {
            logger.info("Found Array ID: " + arr.getId() + " (Elements: " + arr.length() + ")");
        }

        logger.info("\n=== REPOSITORY SORTING BY LENGTH ===");
        repository.sort(CustomArrayComparator.BY_LENGTH);

        for (CustomIntArray arr : repository.getStorage()) {
            logger.info("ID: " + arr.getId() + " | Length: " + arr.length());
        }

        logger.info("\n=== TEST: UPDATING EXISTING ARRAY DATA (ID: 1) ===");
        try {
            List<CustomIntArray> all = repository.getStorage();
            if (!all.isEmpty()) {
                CustomIntArray firstArray = all.get(0);
                long id = firstArray.getId();

                WarehouseStats oldStats = arrayWarehouse.getStats(id);
                logger.info("Before update: ID=" + id + " | Data=" + firstArray + " | Sum=" + oldStats.sum());

                logger.info("Updating data to [100, 100, 100]...");
                firstArray.setArray(new int[]{100, 100, 100});

                WarehouseStats newStats = arrayWarehouse.getStats(id);
                logger.info("After update:  ID=" + id + " | Data=" + firstArray + " | NEW Sum=" + newStats.sum());
            }
        } catch (ArrayProcessingException e) {
            logger.error("Update test failed", e);
        }

        logger.info("=== SYSTEM SHUTDOWN ===");
    }
}