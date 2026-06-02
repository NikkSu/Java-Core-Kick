package by.course.innovice.reader;

import by.course.innovice.exception.ArrayProcessingException;
import java.util.List;

public interface DataReader {
    List<String> readLines(String filePath) throws ArrayProcessingException;
}