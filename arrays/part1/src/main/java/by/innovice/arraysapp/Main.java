package by.innovice.arraysapp;

import by.innovice.arraysapp.facade.ArrayProcessingFacade;
import by.innovice.arraysapp.factory.impl.CustomDoubleArrayFactoryImpl;
import by.innovice.arraysapp.factory.impl.CustomIntArrayFactoryImpl;
import by.innovice.arraysapp.parser.impl.ArrayDataParserImpl;
import by.innovice.arraysapp.parser.impl.DoubleDataParserImpl;
import by.innovice.arraysapp.reader.impl.FileDataReaderImpl;
import by.innovice.arraysapp.service.impl.ArrayMathServiceImpl;
import by.innovice.arraysapp.service.impl.ArraySortServiceImpl;
import by.innovice.arraysapp.validator.impl.ArrayLineValidatorImpl;
import by.innovice.arraysapp.validator.impl.DoubleArrayLineValidatorImpl;

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