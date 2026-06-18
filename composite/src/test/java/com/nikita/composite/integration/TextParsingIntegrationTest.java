package com.nikita.composite.integration;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.exception.TextProcessingException;
import com.nikita.composite.parser.TextParser;
import com.nikita.composite.parser.impl.LexemeParserImpl;
import com.nikita.composite.parser.impl.ParagraphParserImpl;
import com.nikita.composite.parser.impl.SentenceParserImpl;
import com.nikita.composite.parser.impl.SymbolParserImpl;
import com.nikita.composite.parser.impl.WordParserImpl;
import com.nikita.composite.reader.impl.FileTextDataReaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextParsingIntegrationTest {

    private TextParser chainParser;
    private FileTextDataReaderImpl reader;

    @BeforeEach
    void setUp() {
        reader = new FileTextDataReaderImpl();

        SymbolParserImpl symbolParser = new SymbolParserImpl();
        WordParserImpl wordParser = new WordParserImpl(symbolParser);
        LexemeParserImpl lexemeParser = new LexemeParserImpl(wordParser);
        SentenceParserImpl sentenceParser = new SentenceParserImpl(lexemeParser);
        chainParser = new ParagraphParserImpl(sentenceParser);
    }

    @Test
    void testParseAndReconstruct_ValidText_ReconstructsExactly(@TempDir Path tempDir) throws IOException, TextProcessingException {
        Path tempFile = tempDir.resolve("test.txt");
        String originalText = "Hello, world!\nThis is a test. Bye.";
        Files.writeString(tempFile, originalText);
        String filePath = tempFile.toString();

        String readText = reader.readAllText(filePath);

        TextComposite rootComposite = new TextComposite(ComponentType.TEXT);

        chainParser.parse(rootComposite, readText);
        String reconstructedText = rootComposite.reconstruct();

        String trimmedOriginal = originalText.trim();
        String trimmedReconstructed = reconstructedText.trim();

        assertEquals(trimmedOriginal, trimmedReconstructed);
    }
}