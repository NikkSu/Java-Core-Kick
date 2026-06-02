package com.nikita.arraysapp;

import com.nikita.arraysapp.facade.ArrayProcessingFacade;
import com.nikita.arraysapp.factory.impl.CustomDoubleArrayFactoryImpl;
import com.nikita.arraysapp.factory.impl.CustomIntArrayFactoryImpl;
import com.nikita.arraysapp.parser.impl.ArrayDataParserImpl;
import com.nikita.arraysapp.parser.impl.DoubleDataParserImpl;
import com.nikita.arraysapp.reader.impl.FileDataReaderImpl;
import com.nikita.arraysapp.service.impl.ArrayMathServiceImpl;
import com.nikita.arraysapp.service.impl.ArraySortServiceImpl;
import com.nikita.arraysapp.validator.impl.ArrayLineValidatorImpl;
import com.nikita.arraysapp.validator.impl.DoubleArrayLineValidatorImpl;

public class Main {
    public static void main(String[] args) {

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

        facade.processFile("data/arrays.txt");
    }
}