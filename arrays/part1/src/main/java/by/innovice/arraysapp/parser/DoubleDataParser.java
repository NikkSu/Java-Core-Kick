package by.innovice.arraysapp.parser;
import by.innovice.arraysapp.exception.ArrayProcessingException;

public interface DoubleDataParser {
    double[] parseToDoubleArray(String line) throws ArrayProcessingException;
}