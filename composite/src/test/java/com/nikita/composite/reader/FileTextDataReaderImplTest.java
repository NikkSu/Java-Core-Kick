package com.nikita.composite.reader;

import com.nikita.composite.exception.TextProcessingException;
import com.nikita.composite.reader.impl.FileTextDataReaderImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileTextDataReaderImplTest {

    private final FileTextDataReaderImpl reader = new FileTextDataReaderImpl();

    @Test
    void readAllText_NullPath_ThrowsException() {
        assertThrows(TextProcessingException.class, () -> {
            reader.readAllText(null);
        });
    }

    @Test
    void readAllText_InvalidPath_ThrowsException() {
        String badPath = "invalid_path.txt";

        assertThrows(TextProcessingException.class, () -> {
            reader.readAllText(badPath);
        });
    }
}