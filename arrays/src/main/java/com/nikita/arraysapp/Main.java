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
import com.nikita.arraysapp.warehouse.Warehouse;
import com.nikita.arraysapp.warehouse.WarehouseCalculator;
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
        Warehouse warehouse = Warehouse.getInstance();
        WarehouseCalculator calculator = new WarehouseCalculator(mathService);

        repository.getObserver().subscribe(calculator::recalculateAll);

        logger.info("\n=== PROCESSING FILE ===");
        facade.processFile("arrays/data/arrays.txt");


        logger.info("\n=== WAREHOUSE STATS (Auto-calculated by Observer) ===");
        List<CustomIntArray> storage = repository.getStorage();
        for (CustomIntArray arr : storage) {
            WarehouseStats stats = warehouse.getStats(arr.getId());
            if (stats != null) {
                logger.info(String.format("Array ID: %d | Sum=%d, Max=%d, Min=%d, Avg=%.2f",
                        arr.getId(), stats.sum(), stats.max(), stats.min(), stats.average()));
            } else {
                logger.warn("Stats for Array ID " + arr.getId() + " not found!");
            }
        }


        // 5. ТЕСТ: РУЧНОЕ ДОБАВЛЕНИЕ (Проверяем авто-обновление склада)
        logger.info("\n=== TEST: ADDING NEW ARRAY ===");
        try {
            CustomIntArray newArray = intFactory.createArray(new int[]{900, 100});
            logger.info("Created manual array with ID: " + newArray.getId());

            repository.add(newArray); // <--- Тут Observer должен сработать сам!

            WarehouseStats newStats = warehouse.getStats(newArray.getId());
            if (newStats != null) {
                logger.info("Auto-calculated stats for NEW array: Sum=" + newStats.sum());
            }
        } catch (ArrayProcessingException e) {
            logger.error("Error creating manual array", e);
        }


        // 6. ТЕСТ: СПЕЦИФИКАЦИЯ (ПОИСК)
        logger.info("\n=== SPECIFICATION SEARCH ===");
        int sumThreshold = 50;
        logger.info("Looking for arrays with SUM > " + sumThreshold);

        List<CustomIntArray> richArrays = repository.find(new SumGreaterThanSpecification(sumThreshold));
        for (CustomIntArray arr : richArrays) {
            logger.info("Found Array ID: " + arr.getId() + " (Elements: " + arr.length() + ")");
        }


        // 7. ТЕСТ: СОРТИРОВКА (COMPARATOR)
        logger.info("\n=== REPOSITORY SORTING BY LENGTH ===");
        repository.sort(CustomArrayComparator.BY_LENGTH);

        for (CustomIntArray arr : repository.getStorage()) {
            logger.info("ID: " + arr.getId() + " | Length: " + arr.length());
        }

        logger.info("=== SYSTEM SHUTDOWN ===");
    }
}