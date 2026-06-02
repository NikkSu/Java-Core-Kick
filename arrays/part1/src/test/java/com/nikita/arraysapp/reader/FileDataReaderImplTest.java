package com.nikita.arraysapp.reader;

import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.reader.impl.FileDataReaderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileDataReaderImplTest {

    private final FileDataReaderImpl reader = new FileDataReaderImpl();

    @Test
    void testReadLines_ValidFile_ReturnsLines(@TempDir Path tempDir) throws IOException, ArrayProcessingException {
        Path tempFile = tempDir.resolve("test.txt");
        Files.writeString(tempFile, "1, 2, 3");
        String filePath = tempFile.toString();
        int expectedSize = 1;

        List<String> lines = reader.readLines(filePath);

        int actualSize = lines.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void testReadLines_NonExistentFile_ThrowsException() {
        String fakePath = "non_existent_file.txt";

        assertThrows(ArrayProcessingException.class, () -> {
            reader.readLines(fakePath);
        });
    }

    @Test
    void testReadLines_NullPath_ThrowsException() {
        String nullPath = null;

        assertThrows(ArrayProcessingException.class, () -> {
            reader.readLines(nullPath);
        });
    }
}