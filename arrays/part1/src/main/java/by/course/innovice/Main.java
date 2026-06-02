package by.course.innovice;

import by.course.innovice.facade.ArrayProcessingFacade;
import by.course.innovice.factory.impl.CustomDoubleArrayFactoryImpl;
import by.course.innovice.factory.impl.CustomIntArrayFactoryImpl;
import by.course.innovice.parser.impl.ArrayDataParserImpl;
import by.course.innovice.parser.impl.DoubleDataParserImpl;
import by.course.innovice.reader.impl.FileDataReaderImpl;
import by.course.innovice.service.impl.ArrayMathServiceImpl;
import by.course.innovice.service.impl.ArraySortServiceImpl;
import by.course.innovice.validator.impl.ArrayLineValidatorImpl;
import by.course.innovice.validator.impl.DoubleArrayLineValidatorImpl;

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