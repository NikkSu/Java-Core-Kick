package by.innovice.arraysapp.reader;

import by.innovice.arraysapp.exception.ArrayProcessingException;
import java.util.List;

public interface DataReader {
    List<String> readLines(String filePath) throws ArrayProcessingException;
}