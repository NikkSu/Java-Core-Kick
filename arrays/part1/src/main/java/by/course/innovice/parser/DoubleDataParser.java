package by.course.innovice.parser;
import by.course.innovice.exception.ArrayProcessingException;

public interface DoubleDataParser {
    double[] parseToDoubleArray(String line) throws ArrayProcessingException;
}