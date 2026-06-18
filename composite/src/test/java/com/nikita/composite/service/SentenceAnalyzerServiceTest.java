package com.nikita.composite.service;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.entity.TextComposite;
import com.nikita.composite.parser.impl.LexemeParser;
import com.nikita.composite.parser.impl.SentenceParser;
import com.nikita.composite.parser.impl.SymbolParser;
import com.nikita.composite.parser.impl.WordParser;
import com.nikita.composite.service.impl.SentenceAnalyzerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SentenceAnalyzerServiceTest {

    private final SentenceAnalyzerServiceImpl analyzerService = new SentenceAnalyzerServiceImpl();
    private SentenceParser sentenceParser;
    private TextComposite rootText;

    @BeforeEach
    void setUp() {
        SymbolParser symbolParser = new SymbolParser();
        WordParser wordParser = new WordParser(symbolParser);
        LexemeParser lexemeParser = new LexemeParser(wordParser);
        sentenceParser = new SentenceParser(lexemeParser);

        rootText = new TextComposite(ComponentType.TEXT);
    }

    @Test
    void testFindSentencesWithIdenticalWords_TextWithDuplicates_ReturnsCorrectList() {
        String rawText = "This is a normal sentence. This sentence has a has duplicate. Another normal one.";
        sentenceParser.parse(rootText, rawText);

        int expectedSize = 1;

        List<TextComponent> result = analyzerService.findSentencesWithIdenticalWords(rootText);

        int actualSize = result.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void testFindSentencesWithIdenticalWords_TextWithoutDuplicates_ReturnsEmptyList() {
        String rawText = "Hello world! How are you?";
        sentenceParser.parse(rootText, rawText);

        int expectedSize = 0;

        List<TextComponent> result = analyzerService.findSentencesWithIdenticalWords(rootText);

        int actualSize = result.size();
        assertEquals(expectedSize, actualSize);
    }
}